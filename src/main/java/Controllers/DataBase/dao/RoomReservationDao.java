package Controllers.DataBase.dao;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.models.*;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RoomReservationDao extends CommonDao{
    public RoomReservationDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public List<RoomReservation> informationsAboutReservations(LocalDate low, LocalDate high){
        List<RoomReservation> roomReservations = new ArrayList<>();
        try {
            QueryBuilder<RoomReservation, Integer> roomReservationQB = getQueryBuilder(RoomReservation.class);
            QueryBuilder<Room, Integer> roomQB = getQueryBuilder(Room.class);
            roomQB.where().eq("floor", 0).and().eq("pavilon", "A");
            QueryBuilder<Customer, Integer> customerQB = getQueryBuilder(Customer.class);
            QueryBuilder<Reservation, Integer> reservationQB = getQueryBuilder(Reservation.class);

            reservationQB.where().between("arrival_date", low, high)
                    .or().between("departure_date", low, high);

            reservationQB.join(customerQB);
            roomReservationQB.join(roomQB).join(reservationQB);

            PreparedQuery<RoomReservation> preparedQuery = roomReservationQB.prepare();
            roomReservations = getDao(RoomReservation.class).query(preparedQuery);
            return roomReservations;
        }catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
            return roomReservations;
        }
    }

//    public boolean isReserved(LocalDate low, LocalDate high, int id){
//        List<RoomReservation> roomReservations = new ArrayList<>();
//        try {
//            QueryBuilder<RoomReservation, Integer> roomReservationQB = getQueryBuilder(RoomReservation.class);
//            QueryBuilder<Room, Integer> roomQB = getQueryBuilder(Room.class);
//            roomQB.where().eq("floor", 0).and().eq("pavilon", "A")
//                    .and().eq("id_room", id);
//            QueryBuilder<Customer, Integer> customerQB = getQueryBuilder(Customer.class);
//            QueryBuilder<Reservation, Integer> reservationQB = getQueryBuilder(Reservation.class);
//
//            reservationQB.where().between("arrival_date", low, high)
//                    .or().between("departure_date", low, high);
//
//            reservationQB.join(customerQB);
//            roomReservationQB.join(roomQB).join(reservationQB);
//
//            PreparedQuery<RoomReservation> preparedQuery = roomReservationQB.prepare();
//            roomReservations = getDao(RoomReservation.class).query(preparedQuery);
//
//            if(roomReservations.isEmpty())
//                return false;
//            else
//                return true;
//
//        }catch (SQLException e) {
//            DialogsUtils.errorDialog(e.getMessage());
//            return true;
//        }
//    }
}
