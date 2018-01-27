package Controllers.DataBase.models;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User implements BaseModel{
    public User(){
    }

    @DatabaseField(columnName = "id_user", generatedId = true)
    private int idUser;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "surname", canBeNull = false)
    private String surname;

    @DatabaseField(columnName = "nick", canBeNull = false, unique = true)
    private String nick;

    @DatabaseField(columnName = "phone", canBeNull = false)
    private int phone;

    @DatabaseField(columnName = "email", unique = true)
    private String email;

    @DatabaseField(columnName = "adress", canBeNull = false)
    private String adress;

    @DatabaseField(columnName = "password", canBeNull = false)
    private String password;

    @DatabaseField(columnName = "access", canBeNull = false, defaultValue = "false")
    private boolean access;

    @DatabaseField(columnName = "is_logged_in", canBeNull = false, defaultValue = "false")
    private Boolean isLoggedIn;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nick='" + nick + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", password='" + password + '\'' +
                ", access=" + access +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }
}
