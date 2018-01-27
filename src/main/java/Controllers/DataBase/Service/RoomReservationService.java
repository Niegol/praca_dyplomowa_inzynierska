package Controllers.DataBase.Service;

import Controllers.DataBase.Converters.RoomReservationConverter;
import Controllers.DataBase.FXModels.RoomReservationFX;
import Controllers.DataBase.dao.RoomReservationDao;
import Controllers.DataBase.dbutilies.DbMenager;
import Controllers.DataBase.models.RoomReservation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;


public class RoomReservationService {
    private ObservableList<RoomReservationFX> roomReservationFXObservableList = FXCollections.observableArrayList();
    private ObservableList<String> stringDatesObservableList = FXCollections.observableArrayList();

    public void init(LocalDate low, LocalDate high){
        String date;
        this.stringDatesObservableList.clear();
        for(LocalDate iterator = low; iterator.isBefore(high) | iterator.isEqual(high); iterator = iterator.plusDays(1)){
            date = iterator.toString();

           this.stringDatesObservableList.add(date);
        }

        RoomReservationDao roomReservationDao = new RoomReservationDao(DbMenager.getConnectionSource());
        List<RoomReservation> roomReservations = roomReservationDao.informationsAboutReservations(low,high);
        this.roomReservationFXObservableList.clear();
        roomReservations.forEach(roomReservation ->{
            RoomReservationFX roomReservationFX = RoomReservationConverter.convertToRoomReservationFX(roomReservation);
            this.roomReservationFXObservableList.add(roomReservationFX);
        });
        DbMenager.closeConnectionSource();
    }

    public void saveInDB(RoomReservation roomReservation){
        RoomReservationDao roomReservationDao = new RoomReservationDao(DbMenager.getConnectionSource());
        roomReservationDao.createOrUpdate(roomReservation);
        DbMenager.closeConnectionSource();
    }

//    public boolean isReserved(LocalDate low, LocalDate high, int id){
//        boolean bool;
//        RoomReservationDao roomReservationDao = new RoomReservationDao(DbMenager.getConnectionSource());
//        bool = roomReservationDao.isReserved(low,high,id);
//        DbMenager.closeConnectionSource();
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
