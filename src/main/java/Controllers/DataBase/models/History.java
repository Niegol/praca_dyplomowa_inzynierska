package Controllers.DataBase.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "history_of_reservations")
public class History implements BaseModel {
    public History (){
    }

    @DatabaseField(columnName = "id_history", generatedId = true)
    private int idHistory;

    @DatabaseField(columnName = "client_name", canBeNull = false)
    private String clientName;

    @DatabaseField(columnName = "reservation_date", canBeNull = false)
    private Date reservationDate;

    @DatabaseField(columnName = "user_nick", canBeNull = false)
    private String userNick;

    @DatabaseField(columnName = "changing_date")
    private Date changingDate;

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public Date getChangingDate() {
        return changingDate;
    }

    public void setChangingDate(Date changingDate) {
        this.changingDate = changingDate;
    }

    @Override
    public String toString() {
        return "History{" +
                "idHistory=" + idHistory +
                ", clientName='" + clientName + '\'' +
                ", reservationDate=" + reservationDate +
                ", userNick='" + userNick + '\'' +
                ", changingDate=" + changingDate +
                '}';
    }
}
