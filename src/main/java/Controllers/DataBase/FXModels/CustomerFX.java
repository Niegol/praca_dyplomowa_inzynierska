package Controllers.DataBase.FXModels;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty nip = new SimpleStringProperty();
    private StringProperty company = new SimpleStringProperty();

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

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public String getNip() {
        return nip.get();
    }

    public StringProperty nipProperty() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip.set(nip);
    }

    public String getCompany() {
        return company.get();
    }

    public StringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    @Override
    public String toString() {
        return id.getValue() + ". " + surname.getValue() + " " + name.getValue();
    }
}
