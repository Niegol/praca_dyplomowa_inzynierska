package Controllers.DataBase.FXModels;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasa odpowiadająca klasie modelu Room. Jest jego widokową reprezentacją. Posiada argumenty, których możliwe
 * jest wyświtlanie w oknach JavaFX oraz gettery i settery.
 */
public class RoomFX {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty roomNumber = new SimpleStringProperty();
    IntegerProperty floor = new SimpleIntegerProperty();
    StringProperty pavilon = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getRoomNumber() {
        return roomNumber.get();
    }

    public StringProperty roomNumberProperty() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    public int getFloor() {
        return floor.get();
    }

    public IntegerProperty floorProperty() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor.set(floor);
    }

    public String getPavilon() {
        return pavilon.get();
    }

    public StringProperty pavilonProperty() {
        return pavilon;
    }

    public void setPavilon(String pavilon) {
        this.pavilon.set(pavilon);
    }

    @Override
    public String toString() {
        return "RoomFX{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", floor=" + floor +
                ", pavilon=" + pavilon +
                '}';
    }
}
