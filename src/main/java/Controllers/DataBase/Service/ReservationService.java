package Controllers.DataBase.Service;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.Converters.DateAndStringConverter;
import Controllers.DataBase.Converters.ReservationConverter;
import Controllers.DataBase.FXModels.ReservationFX;
import Controllers.DataBase.FXModels.RoomFX;
import Controllers.DataBase.dao.ReservationDao;
import Controllers.DataBase.dbutilies.DbManager;
import Controllers.DataBase.models.Reservation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class ReservationService {
    private ObservableList<ReservationFX> reservationFXObservableList = FXCollections.observableArrayList();
    private ObservableList<RoomFX> reservationFXSelectedRooms = FXCollections.observableArrayList();
    private ObjectProperty<ReservationFX> reservation = new SimpleObjectProperty<>(new ReservationFX());
    private ObjectProperty<ReservationFX> reservationEdit = new SimpleObjectProperty<>();

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

    public void addSelectedRoom(RoomFX roomFX){
        this.reservationFXSelectedRooms.add(roomFX);
    }

    public void removeSelectedRoom(RoomFX roomFX){
        this.reservationFXSelectedRooms.remove(roomFX);
    }


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

    public void saveOrUpdate(ReservationFX reservationFX){
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
}
