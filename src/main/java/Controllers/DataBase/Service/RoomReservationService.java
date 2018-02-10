package Controllers.DataBase.Service;

import Controllers.DataBase.Converters.RoomReservationConverter;
import Controllers.DataBase.FXModels.RoomReservationFX;
import Controllers.DataBase.dao.RoomReservationDao;
import Controllers.DataBase.dbutilies.DbManager;
import Controllers.DataBase.models.Reservation;
import Controllers.DataBase.models.RoomReservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

/**
 * Klasa kontrolująca wywoływanie obiektów klasy RoomReservationDao. Tworzy je w przypadkach, gdy spełnione są odpowiednie
 * warunki. Posiada seterry i gettery argumetów tej klasy, dzięki którym możliwe jest operowanie na tychże argumentach.
 */
public class RoomReservationService {
    /**
     * Lista obiektów obserwowalnych przechowująca obeikty klasy RoomResrvationFX, niosące informację o wszystkich
     * dokonanych rezerwacjach.
     */
    private ObservableList<RoomReservationFX> roomReservationFXObservableList = FXCollections.observableArrayList();
    /**
     * Lista obserwalnych elementów klasy String, zawiera kolejne występujące po sobie daty na zadanym przedziale jej
     * inicjalizacji.
     */
    private ObservableList<String> stringDatesObservableList = FXCollections.observableArrayList();


    /**
     * Metoda inicjalizująca stringDatesObservableList oraz roomReservationFXObservableList na zadanym przedziale
     * czasowym.
     * @param low data od której ma nastąpić wyszukiwanie.
     * @param high data do której ma nastąpić wyszukiwanie.
     */
    public void init(LocalDate low, LocalDate high){
        String date;
        this.stringDatesObservableList.clear();
        for(LocalDate iterator = low; iterator.isBefore(high) | iterator.isEqual(high); iterator = iterator.plusDays(1)){
            date = iterator.toString();

           this.stringDatesObservableList.add(date);
        }

        RoomReservationDao roomReservationDao = new RoomReservationDao(DbManager.getConnectionSource());
        List<RoomReservation> roomReservations = roomReservationDao.informationsAboutReservations(low,high);
        this.roomReservationFXObservableList.clear();
        roomReservations.forEach(roomReservation ->{
            RoomReservationFX roomReservationFX = RoomReservationConverter.convertToRoomReservationFX(roomReservation);
            this.roomReservationFXObservableList.add(roomReservationFX);
        });
        DbManager.closeConnectionSource();
    }

    /**
     * Metoda odpowiedzialna za usuwanie wszystkich pokoi przypisanych do rezerwacji. Rozumiane jest to w ten sposób,
     * że usuwane zostają wszystkie rekordy zawierające w sobie obiekt Reservation podany w argumencie.
     * @param reservation rezerwacja, której pokoje zostaną usunięte.
     */
    public void roomsToDelete(Reservation reservation){
        RoomReservationDao roomReservationDao = new RoomReservationDao(DbManager.getConnectionSource());
        List<RoomReservation> roomReservations = roomReservationDao.findByIdReservation(reservation);
        roomReservations.forEach(roomReservation -> {
            roomReservationDao.delete(roomReservation);
        });

        DbManager.closeConnectionSource();
    }


    /**
     * Metoda zapisująca obiekt RoomReservation do bazy danych. W tym miejscu nastepuje przypisanie konkretnego pokoju
     * do konkretnej rezerwacji.
     * @param roomReservation obiekt klasy RoomReservation, który ma zostać zapisany w bazie danych.
     */
    public void saveInDB(RoomReservation roomReservation){
        RoomReservationDao roomReservationDao = new RoomReservationDao(DbManager.getConnectionSource());
        roomReservationDao.createOrUpdate(roomReservation);
        DbManager.closeConnectionSource();
    }

//    public boolean isReserved(LocalDate low, LocalDate high, int id){
//        boolean bool;
//        RoomReservationDao roomReservationDao = new RoomReservationDao(DbManager.getConnectionSource());
//        bool = roomReservationDao.isReserved(low,high,id);
//        DbManager.closeConnectionSource();
//        return bool;
//    }

    public ObservableList<String> getStringDatesObservableList() {
        return stringDatesObservableList;
    }

    public void setStringDatesObservableList(ObservableList<String> stringDatesObservableList) {
        this.stringDatesObservableList = stringDatesObservableList;
    }

    public ObservableList<RoomReservationFX> getRoomReservationFXObservableList() {
        return roomReservationFXObservableList;
    }

    public void setRoomReservationFXObservableList(ObservableList<RoomReservationFX> roomReservationFXObservableList) {
        this.roomReservationFXObservableList = roomReservationFXObservableList;
    }


}
