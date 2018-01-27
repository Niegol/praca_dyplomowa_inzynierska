package Controllers.DataBase.FXModels;

import javafx.beans.property.*;

public class ReservationFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<CustomerFX> customerFX = new SimpleObjectProperty<>();
    private StringProperty ammountOfPeople = new SimpleStringProperty();
    private StringProperty arrivalDate = new SimpleStringProperty();
    private StringProperty departureDate = new SimpleStringProperty();
    private StringProperty startingMeal = new SimpleStringProperty();
    private StringProperty endingMeal = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();
    private StringProperty creationDate = new SimpleStringProperty();
    private UserFX userFX;
    private StringProperty comment = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public CustomerFX getCustomerFX() {
        return customerFX.get();
    }

    public ObjectProperty<CustomerFX> customerFXProperty() {
        return customerFX;
    }

    public void setCustomerFX(CustomerFX customerFX) {
        this.customerFX.set(customerFX);
    }

    public String getAmmountOfPeople() {
        return ammountOfPeople.get();
    }

    public StringProperty ammountOfPeopleProperty() {
        return ammountOfPeople;
    }

    public void setAmmountOfPeople(String ammountOfPeople) {
        this.ammountOfPeople.set(ammountOfPeople);
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

    public String getStartingMeal() {
        return startingMeal.get();
    }

    public StringProperty startingMealProperty() {
        return startingMeal;
    }

    public void setStartingMeal(String startingMeal) {
        this.startingMeal.set(startingMeal);
    }

    public String getEndingMeal() {
        return endingMeal.get();
    }

    public StringProperty endingMealProperty() {
        return endingMeal;
    }

    public void setEndingMeal(String endingMeal) {
        this.endingMeal.set(endingMeal);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getCreationDate() {
        return creationDate.get();
    }

    public StringProperty creationDateProperty() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate.set(creationDate);
    }

    public UserFX getUserFX() {
        return userFX;
    }

    public void setUserFX(UserFX userFX) {
        this.userFX = userFX;
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    @Override
    public String toString() {
        return "ReservationFX{" +
                "id=" + id +
                ", customerFX=" + customerFX +
                ", ammountOfPeople=" + ammountOfPeople +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", startingMeal=" + startingMeal +
                ", endingMeal=" + endingMeal +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", userFX=" + userFX +
                ", comment=" + comment +
                '}';
    }
}
