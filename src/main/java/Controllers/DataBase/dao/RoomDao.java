package Controllers.DataBase.dao;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.models.Room;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao extends CommonDao {

    public RoomDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

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
