package Controllers.DataBase.dao;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.models.Room;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa rozszerzająca klasę CommonDao i umożliwiająca genrowanie kwerend związanych z obiektami klasy Room.
 */
public class RoomDao extends CommonDao {
    /**
     * Konstruktor przyjmujący status połączenia z bazą danych. Każdy stworzony obiekt musi posiadać tą informację.
     * @param connectionSource status połączenia z bazą danych.
     */
    public RoomDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    /**
     * Metoda zwracająca wszystkie obiekty Room z zadanego budynku i piętra. Tworzona jest kwerenda przy użyciu
     * querryBuilder oraz metod selectColumns(), where() oraz eq() (ang. equals). Jeżeli połączenie z bazą danych będzie
     * nieprawidłowe, wówczas wyświtlony zostanie odpowiedni komunikat.
     * @param pavilon numer pavilonu, na którym znajduje się pokój.
     * @param floor numer piętra, na którym znajduje się pokój.
     * @return lista wszystkich pokoi o zadanaych parametrach.
     */
    public List<Room> getRooms(String pavilon, int floor){
        List<Room> result = new ArrayList<Room>();
        try {
            QueryBuilder<Room, Integer> queryBuilder = getQueryBuilder(Room.class);
            queryBuilder.selectColumns("room_number").where().eq("pavilon", pavilon).and().eq("floor", floor);
            PreparedQuery<Room> roomPreparedQuery = queryBuilder.prepare();
            result = getDao(Room.class).query(roomPreparedQuery);
            return result;

        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
            return result;
        }
    }
}
