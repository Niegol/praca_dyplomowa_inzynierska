package Controllers.DataBase.Service;

import Controllers.DataBase.Converters.RoomConverter;
import Controllers.DataBase.FXModels.RoomFX;
import Controllers.DataBase.dao.RoomDao;
import Controllers.DataBase.dbutilies.DbManager;
import Controllers.DataBase.models.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Klasa kontrolująca wywoływanie obiektów klasy RoomDao. Tworzy je w przypadkach, gdy spełnione są odpowiednie
 * warunki. Posiada seterry i gettery argumetów tej klasy, dzięki którym możliwe jest operowanie na tychże argumentach.
 */
public class RoomService {
    /**
     * Lista przechowująca wszystkie pokoje z danego pawilonu, z danego piętra. Składa się z obiektów obserwowalnych
     * RoomFX.
     */
    private ObservableList<RoomFX> roomFXObservableList = FXCollections.observableArrayList();

    /**
     * Inicjalizacja listy roomFXObservableList.
     * @param pavilon pawilon, z którego mają zostać wprowadzone pokoje do listy pokoje.
     * @param floor piętro pawilonu, z którego mają zostać wprowadzone pokoje do listy.
     */
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


    /**
     * Zwraca listę roomFXObservableList.
     * @return lista wszystkich pokoi z danego pawilonu i piętra.
     */
    public ObservableList<RoomFX> getRoomFXObservableList() {
        return roomFXObservableList;
    }

    public void setRoomFXObservableList(ObservableList<RoomFX> roomFXObservableList) {
        this.roomFXObservableList = roomFXObservableList;
    }


}
