package Controllers.DataBase.FXModels;

import javafx.beans.property.*;

/**
 * Klasa odpowiadająca klasie modelu History. Jest jego widokową reprezentacją. Posiada argumenty, których możliwe
 * jest wyświtlanie w oknach JavaFX oraz gettery i settery.
 */
public class HistoryFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty customerSurname = new SimpleStringProperty();
    private StringProperty arrivalDate = new SimpleStringProperty();
    private StringProperty departureDate = new SimpleStringProperty();
    private StringProperty changingDate = new SimpleStringProperty();
    private ObjectProperty<UserFX> userFX = new SimpleObjectProperty<>();
    private StringProperty amountPeople = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getCustomerSurname() {
        return customerSurname.get();
    }

    public StringProperty customerSurnameProperty() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname.set(customerSurname);
    }

    public String getArrivalDate() {
        return arrivalDate.get();
    }

    public StringProperty arrivalDateProperty() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate.set(arrivalDate);
    }

    public String getDepartureDate() {
        return departureDate.get();
    }

    public StringProperty departureDateProperty() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate.set(departureDate);
    }

    public String getChangingDate() {
        return changingDate.get();
    }

    public StringProperty changingDateProperty() {
        return changingDate;
    }

    public void setChangingDate(String changingDate) {
        this.changingDate.set(changingDate);
    }

    public UserFX getUserFX() {
        return userFX.get();
    }

    public ObjectProperty<UserFX> userFXProperty() {
        return userFX;
    }

    public void setUserFX(UserFX userFX) {
        this.userFX.set(userFX);
    }

    public String getAmountPeople() {
        return amountPeople.get();
    }

    public StringProperty amountPeopleProperty() {
        return amountPeople;
    }

    public void setAmountPeople(String amountPeople) {
        this.amountPeople.set(amountPeople);
    }



    @Override
    public String toString() {
        return "HistoryFX{" +
                "id=" + id +
                ", customerSurname=" + customerSurname +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", changingDate=" + changingDate +
                ", userFX=" + userFX +
                ", amountPeople=" + amountPeople +
                '}';
    }
}
