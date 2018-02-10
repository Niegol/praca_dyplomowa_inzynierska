package Controllers.DataBase.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa, której obiekty niosa informację o pokojach. Rozszerza interfejs BaseModel. Klasa Javowa będąca reprezentacją
 * encji bazodanowej w programie. Odpwowiada za encje typu room. Typem klucza głównego jest zmienna typu integer.
 * Posiada settery oraz gettery dzięki którym ORMLite może odrazu tworzyć obiekty tego typu po otrzymaniu odpowiedzi z
 * bazy danych, dzięki obiektom DAO.
 */
@DatabaseTable(tableName = "rooms")
public class Room implements BaseModel{
    public Room(){
    }

    @DatabaseField(columnName = "id_room", generatedId = true)
    private int idRoom;

    @DatabaseField(columnName = "room_number", canBeNull = false)
    private int roomNumber;

    @DatabaseField(columnName = "floor", canBeNull = false)
    private int floor;

    @DatabaseField(columnName = "pavilon", canBeNull = false)
    private String pavilon;

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getPavilon() {
        return pavilon;
    }

    public void setPavilon(String pavilon) {
        this.pavilon = pavilon;
    }

    @Override
    public String toString() {
        return "Room{" +
                "idRoom=" + idRoom +
                ", roomNumber=" + roomNumber +
                ", floor=" + floor +
                ", pavilon='" + pavilon + '\'' +
                '}';
    }
}
