package Controllers.DataBase.Service;

import Controllers.DataBase.Converters.RoomConverter;
import Controllers.DataBase.FXModels.RoomFX;
import Controllers.DataBase.dao.RoomDao;
import Controllers.DataBase.dbutilies.DbManager;
import Controllers.DataBase.models.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


public class RoomService {
    private ObservableList<RoomFX> roomFXObservableList = FXCollections.observableArrayList();

    public void init(String pavilon, int floor){
        RoomDao roomDao = new RoomDao(DbManager.getConnectionSource());
        List<Room> rooms = roomDao.getRooms(pavilon,floor);
        this.roomFXObservableList.clear();
        rooms.forEach(room -> {
            RoomFX roomFX = RoomConverter.convertToRoomFX(room);
            this.roomFXObservableList.add(roomFX);
        });
        DbManager.closeConnectionSource();
    }



    public ObservableList<RoomFX> getRoomFXObservableList() {
        return roomFXObservableList;
    }

    public void setRoomFXObservableList(ObservableList<RoomFX> roomFXObservableList) {
        this.roomFXObservableList = roomFXObservableList;
    }


}
