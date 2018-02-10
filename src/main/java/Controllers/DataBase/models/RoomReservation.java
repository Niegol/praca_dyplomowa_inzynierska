package Controllers.DataBase.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa, której obiekty niosą ze sobą informację jaki pokój jest przypisany do jakiej rezerwacji oraz do jakiego
 * klienta (Customer). Posiada dwa klucze obce będące obiektami Room oraz Reservation. Rozszerza interfejs BaseModel.
 * Klasa Javowa będąca reprezentacją encji bazodanowej w programie. Odpwowiada za encje typu rooms_reservations. Typem
 * klucza głównego jest zmienna typu integer. Posiada settery oraz gettery dzięki którym ORMLite może odrazu tworzyć
 * obiekty tego typu po otrzymaniu odpowiedzi z bazy danych, dzięki obiektom DAO.
 */
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
