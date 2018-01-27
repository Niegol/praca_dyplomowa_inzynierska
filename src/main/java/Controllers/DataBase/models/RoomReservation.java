package Controllers.DataBase.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "rooms_reservations")
public class RoomReservation implements BaseModel {
    public RoomReservation(){}

    @DatabaseField(columnName = "idrooms_reservations", generatedId = true)
    private int id;

    @DatabaseField(columnName = "id_reservation", foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private Reservation reservation;

    @DatabaseField(columnName = "id_room", foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private Room room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "RoomReservation{" +
                "id=" + id +
                ", reservation=" + reservation +
                ", room=" + room +
                '}';
    }
}
