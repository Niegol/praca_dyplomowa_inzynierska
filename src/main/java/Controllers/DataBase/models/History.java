package Controllers.DataBase.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "history_of_reservations")
public class History implements BaseModel {
    public History (){
    }

    @DatabaseField(columnName = "id_history", generatedId = true)
    private int idHistory;

    @DatabaseField(columnName = "customer_surname", canBeNull = false)
    private String customerSurname;

    @DatabaseField(columnName = "arrival_date", canBeNull = false, format = "yyyy-MM-dd")
    private String arrival;

    @DatabaseField(columnName = "departure_date", canBeNull = false, format = "yyyy-MM-dd")
    private String departure;

    @DatabaseField(columnName = "id_user", foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private User user;

    @DatabaseField(columnName = "changing_date", canBeNull = false, format = "yyyy-MM-dd")
    private String changingDate;

    @DatabaseField(columnName = "amount_people", canBeNull = false)
    private int amountPeople;


    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getChangingDate() {
        return changingDate;
    }

    public void setChangingDate(String changingDate) {
        this.changingDate = changingDate;
    }

    public int getAmountPeople() {
        return amountPeople;
    }

    public void setAmountPeople(int amountPeople) {
        this.amountPeople = amountPeople;
    }


    @Override
    public String toString() {
        return "History{" +
                "idHistory=" + idHistory +
                ", customerSurname='" + customerSurname + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departure='" + departure + '\'' +
                ", user=" + user +
                ", changingDate='" + changingDate + '\'' +
                ", amountPeople=" + amountPeople +
                '}';
    }
}
