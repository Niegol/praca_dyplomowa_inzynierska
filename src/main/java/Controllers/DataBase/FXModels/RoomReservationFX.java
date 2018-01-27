package Controllers.DataBase.FXModels;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class RoomReservationFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private ReservationFX reservationFX;
    private RoomFX roomFX;

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public ReservationFX getReservationFX() {
        return reservationFX;
    }

    public void setReservationFX(ReservationFX reservationFX) {
        this.reservationFX = reservationFX;
    }

    public RoomFX getRoomFX() {
        return roomFX;
    }

    public void setRoomFX(RoomFX roomFX) {
        this.roomFX = roomFX;
    }

    @Override
    public String toString() {
        return "RoomReservationFX{" +
                "id=" + id +
                ", reservationFX=" + reservationFX +
                ", roomFX=" + roomFX +
                '}';
    }
}
