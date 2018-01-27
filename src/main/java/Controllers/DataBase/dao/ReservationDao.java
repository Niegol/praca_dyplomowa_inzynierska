package Controllers.DataBase.dao;

import Controllers.DataBase.models.Reservation;
import com.j256.ormlite.support.ConnectionSource;

import java.util.List;

public class ReservationDao extends CommonDao {

    public ReservationDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public List<Reservation> getReservations(){
        List<Reservation> reservations = queryForAll(Reservation.class);
        reservations.forEach(e-> System.out.println(e));
        return reservations;
    }


}
