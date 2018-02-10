package Controllers.DataBase.Service;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.Converters.DateAndStringConverter;
import Controllers.DataBase.Converters.ReservationConverter;
import Controllers.DataBase.Converters.UserConverter;
import Controllers.DataBase.FXModels.ReservationFX;
import Controllers.DataBase.FXModels.RoomFX;
import Controllers.DataBase.FXModels.RoomReservationFX;
import Controllers.DataBase.dao.HistoryDao;
import Controllers.DataBase.dao.ReservationDao;
import Controllers.DataBase.dbutilies.DbManager;
import Controllers.DataBase.models.History;
import Controllers.DataBase.models.Reservation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

/**
 * Klasa kontrolująca wywoływanie obiektów klasy ReservationDao. Tworzy je w przypadkach, gdy spełnione są odpowiednie
 * warunki. Posiada seterry i gettery argumetów tej klasy, dzięki którym możliwe jest operowanie na tychże argumentach.
 */
public class ReservationService {
    /**
     * Lista obiektów obserwowalnych klasy ReservationFX przechowująca w sobie wszystkie obecne w bazie danych
     * rezerwacje.
     */
    private ObservableList<ReservationFX> reservationFXObservableList = FXCollections.observableArrayList();
    /**
     * Lista obiektów obserwowalnych klasy RoomFX. Jest to lista, która odpowiada wybranym przy rezerwacji pokojom.
     */
    private ObservableList<RoomFX> reservationFXSelectedRooms = FXCollections.observableArrayList();
    /**
     * Obiekt obserwowalny klasy ResevationFX przechowujący informację o obecnie dodaeanej w systemie rezeracji.
     */
    private ObjectProperty<ReservationFX> reservation = new SimpleObjectProperty<>(new ReservationFX());
    /**
     * Obiekt obserwowalny klasy ReservationFX przechowujący w sobieinformację o edytowanej rezerwacji.
     */
    private ObjectProperty<ReservationFX> reservationEdit = new SimpleObjectProperty<>();
    /**
     * Lista obiektów obserwowalnych klasy RoomReservation przechowującej w sobie informacje o rezerwacjach i
     * przypisanych do nich pokojach.
     */
    private ObservableList<RoomReservationFX> roomReservationFXES = FXCollections.observableArrayList();

    /**
     * Metoda inicjalizująca listę reservations wszystkich zapisanych w systemie rezerwacji.
     */
    public void init(){
        ReservationDao reservationDao = new ReservationDao(DbManager.getConnectionSource());
        List<Reservation> reservations = reservationDao.queryForAll(Reservation.class);
        this.reservationFXObservableList.clear();
        reservations.forEach(reservation->{
            ReservationFX reservationFX = ReservationConverter.convertToReservationFX(reservation);
            this.reservationFXObservableList.add(reservationFX);
        });

        DbManager.closeConnectionSource();
    }

    /**
     * Metoda dodaje zaznaczony pokój do listy pokoi, które mają zostać przypisane do rezerwacji.
     * @param roomFX pokój (Room), który ma zostać dodany do rezerwacji.
     */
    public void addSelectedRoom(RoomFX roomFX){
        this.reservationFXSelectedRooms.add(roomFX);
    }

    /**
     * Metoda usuwająca odznaczony pokój przy doborze pokoi do rezerwacji.
     * @param roomFX pokój, który ma zostać usunięty z listy dodanych do rezerwacji pokoi.
     */
    public void removeSelectedRoom(RoomFX roomFX){
        this.reservationFXSelectedRooms.remove(roomFX);
    }


    /**
     * Metoda zwraca ostatnią dokonaną rezerwację.
     * @return ostatnia dokonana rezerwacja.
     */
    public ReservationFX getLastReservation(){
        ReservationFX reservation;
        int i;
        i = reservationFXObservableList.size();
        if(i > 0) {
            reservation = reservationFXObservableList.get(i - 1);
            return reservation;
        }else
            return this.reservation.get();
    }

    /**
     * Metoda usuwa rezerwację (Reservation) odpowiadającą obiektowi reservationEdit. Przedjej wywołaniem następuje
     * zapisanie tejże rezerwacji do historii usuniętych rezerwacji (History), kolejno następuje usunięcie wszystkich
     * pokoi do niej przypisanych.
     */
    public void deleteInDB(){
        History history = new History();
        DialogsUtils.communicat(this.reservationEdit.get().getAmmountOfPeople());
        history.setAmountPeople(Integer.parseInt(this.reservationEdit.get().getAmmountOfPeople()));
        history.setUser(UserConverter.convertToUser(this.reservationEdit.get().getUserFX()));
        history.setArrival(this.reservationEdit.get().getArrivalDate());
        history.setDeparture(this.reservationEdit.get().getDepartureDate());
        history.setCustomerSurname(this.reservationEdit.get().getCustomerFX().getSurname() +
                ", " +this.reservationEdit.get().getCustomerFX().getName()+ ", "
                + this.reservationEdit.get().getCustomerFX().getPhone());
        history.setChangingDate(String.valueOf(LocalDate.now()));
        HistoryDao historyDao = new HistoryDao(DbManager.getConnectionSource());
        historyDao.createOrUpdate(history);
        DbManager.closeConnectionSource();


        RoomReservationService roomReservationService = new RoomReservationService();
        roomReservationService.roomsToDelete(ReservationConverter.convertToReservation(this.reservationEdit.get()));
        ReservationDao reservationDao = new ReservationDao(DbManager.getConnectionSource());
        reservationDao.deleteById(Reservation.class, this.getReservationEdit().getId());
        DbManager.closeConnectionSource();
        this.init();
    }

    /**
     * Metoda zapisująca do bazy danych rezerwację pod warunkiem spełnienia wszystkich wymaganych przy jej dodawaniu
     * warunków: zakres dat musi być poprawny, musi posiadać status (zarezerwowana, wpłacona zaliczka, opłacona),
     * wprowadzana liczba zawierająca informację o ilości osób przypisanych do rezerwacji musi być odpowiedniego formatu
     * (może zawierać tylko liczby0.Korzysta z metody należącej do klasy
     *  saveOrUpdate.
     */
    public void saveInDB(){
        System.out.println(this.getReservation().getArrivalDate());
        LocalDate localDateARR = DateAndStringConverter.stringToLocalDate(this.getReservation().getArrivalDate());
        LocalDate localDateDEP = DateAndStringConverter.stringToLocalDate(this.getReservation().getDepartureDate());
        if((!(localDateARR.isAfter(localDateDEP) | localDateDEP.isBefore(localDateARR)))){
            if (this.getReservation().getStatus().equals("Reserved") |
                    this.getReservation().getStatus().equals("Paid") |
                    this.getReservation().getStatus().equals("Piece")) {
                this.getReservation().setCreationDate(LocalDate.now().toString());
                try {
                    this.saveOrUpdate(this.getReservation());
                    DialogsUtils.communicat("Reservation has been added!");
                }catch (NumberFormatException e){
                    DialogsUtils.errorDialog("Ammount of people must be number");
                }
            }else
                DialogsUtils.errorDialog("Wrong reservation status!");
        }else
            DialogsUtils.errorDialog("Arrival date is after departure, or departure date is before arrival!");
    }

    /**
     * Metoda zapisująca edytowaną zmianę, jeżeli jest ona możliwa do wykonania. Korzysta z metody należącej do klasy
     *  saveOrUpdate.
     */
    public void saveEditInDB(){
        LocalDate localDateARR = DateAndStringConverter.stringToLocalDate(this.getReservationEdit().getArrivalDate());
        LocalDate localDateDEP = DateAndStringConverter.stringToLocalDate(this.getReservationEdit().getDepartureDate());
        if((!(localDateARR.isAfter(localDateDEP) | localDateDEP.isBefore(localDateARR)))){
            if (this.getReservationEdit().getStatus().equals("Reserved") |
                    this.getReservationEdit().getStatus().equals("Paid") |
                    this.getReservationEdit().getStatus().equals("Piece")) {
                this.getReservationEdit().setCreationDate(LocalDate.now().toString());
                this.saveOrUpdate(this.getReservationEdit());
            }else
                DialogsUtils.errorDialog("Wrong reservation status!");
        }else
            DialogsUtils.errorDialog("Arrival date is after departure, or departure date is before arrival!");
    }

    /**
     * Metoda zapisuje obiekt klay ReservationFX z widoku do bazy danych. Jest używana przez metody, klasy
     * ReservationServise, które kontrolują, czy wprowadzone dane zostać zapisane. Tworzy ona obiekt klasy ReservationDao
     * przekazując mu połączenie do bazy danych. Następnie następuje konwersja do do obiektu Reservation, następnie jest
     * on zapisywany, a połączenie zostaje zamknięte.
     * @param reservationFX obiekt widoku ResrvationFX, który ma zostać zapisany.
     */
    private void saveOrUpdate(ReservationFX reservationFX){
        ReservationDao reservationDao = new ReservationDao(DbManager.getConnectionSource());
        Reservation reservationSave = ReservationConverter.convertToReservation(reservationFX);
        reservationDao.createOrUpdate(reservationSave);
        DbManager.closeConnectionSource();
    }

    public ObservableList<ReservationFX> getReservationFXObservableList() {
        return reservationFXObservableList;
    }

    public void setReservationFXObservableList(ObservableList<ReservationFX> reservationFXObservableList) {
        this.reservationFXObservableList = reservationFXObservableList;
    }

    public ReservationFX getReservation() {
        return reservation.get();
    }

    public ObjectProperty<ReservationFX> reservationProperty() {
        return reservation;
    }

    public void setReservation(ReservationFX reservation) {
        this.reservation.set(reservation);
    }

    public ReservationFX getReservationEdit() {
        return reservationEdit.get();
    }

    public ObjectProperty<ReservationFX> reservationEditProperty() {
        return reservationEdit;
    }

    public void setReservationEdit(ReservationFX reservationEdit) {
        this.reservationEdit.set(reservationEdit);
    }

    public ObservableList<RoomFX> getReservationFXSelectedRooms() {
        return reservationFXSelectedRooms;
    }

    public void setReservationFXSelectedRooms(ObservableList<RoomFX> reservationFXSelectedRooms) {
        this.reservationFXSelectedRooms = reservationFXSelectedRooms;
    }

    public ObservableList<RoomReservationFX> getRoomReservationFXES() {
        return roomReservationFXES;
    }

    public void setRoomReservationFXES(ObservableList<RoomReservationFX> roomReservationFXES) {
        this.roomReservationFXES = roomReservationFXES;
    }
}
