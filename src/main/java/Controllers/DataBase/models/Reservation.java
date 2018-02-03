package Controllers.DataBase.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "reservations")
public class Reservation implements BaseModel {
    public Reservation() {
    }

    @DatabaseField(columnName = "id_reservation", generatedId = true)
    private int idReservation;

    @DatabaseField(columnName = "id_customer", foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private Customer customer;

    @DatabaseField(columnName = "amount_of_people", canBeNull = false)
    private int amountOfPeople;

    @DatabaseField(columnName = "arrival_date", canBeNull = false, format = "yyyy-MM-dd")
    private String arrivalDate;

    @DatabaseField(columnName = "departure_date", canBeNull = false)
    private String departureDate;

    @DatabaseField(columnName = "starting_meal")
    private String startingMeal;

    @DatabaseField(columnName = "ending_meal")
    private String endingMeal;

    @DatabaseField(columnName = "status", canBeNull = false, defaultValue = "Reserved")
    private String status;

    @DatabaseField(columnName = "creation_date", canBeNull = false)
    private String creationDate;

    @DatabaseField(columnName = "id_user", foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private User user;

    @DatabaseField(columnName = "comment")
    private String comment;

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getStartingMeal() {
        return startingMeal;
    }

    public void setStartingMeal(String startingMeal) {
        this.startingMeal = startingMeal;
    }

    public String getEndingMeal() {
        return endingMeal;
    }

    public void setEndingMeal(String endingMeal) {
        this.endingMeal = endingMeal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", customer=" + customer +
                ", amountOfPeople=" + amountOfPeople +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", startingMeal='" + startingMeal + '\'' +
                ", endingMeal='" + endingMeal + '\'' +
                ", status='" + status + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", user=" + user +
                ", comment='" + comment + '\'' +
                '}';
    }
}

