package Controllers.DataBase.FXModels;

import javafx.beans.property.*;

public class UserFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private StringProperty nick = new SimpleStringProperty();
    private IntegerProperty phone = new SimpleIntegerProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty adress = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private BooleanProperty access = new SimpleBooleanProperty();
    private BooleanProperty isLogged = new SimpleBooleanProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getNick() {
        return nick.get();
    }

    public StringProperty nickProperty() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick.set(nick);
    }

    public int getPhone() {
        return phone.get();
    }

    public IntegerProperty phoneProperty() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAdress() {
        return adress.get();
    }

    public StringProperty adressProperty() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public boolean isAccess() {
        return access.get();
    }

    public BooleanProperty accessProperty() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access.set(access);
    }

    public boolean isIsLogged() {
        return isLogged.get();
    }

    public BooleanProperty isLoggedProperty() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged.set(isLogged);
    }

    @Override
    public String toString() {
        return "UserFX{" +
                "id=" + id +
                ", name=" + name +
                ", surname=" + surname +
                ", nick=" + nick +
                ", phone=" + phone +
                ", email=" + email +
                ", adress=" + adress +
                ", password=" + password +
                ", access=" + access +
                ", isLogged=" + isLogged +
                '}';
    }
}
