package Controllers.DataBase.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Klasa, której atrybuty posiadają wszelkie niezbędne informacje o klientach. Klasa ta rozszerza interfejs BaseModel.
 * Klasa Javowa będąca reprezentacją encji bazodanowej w programie. Odpwowiada za encje typu customer. Typem klucza
 * głównego jest zmienna typu integer. Posiada settery oraz gettery dzięki którym ORMLite może odrazu tworzyć obiekty
 * tego typu po otrzymaniu odpowiedzi z bazy danych, dzięki obiektom DAO.
 */
@DatabaseTable(tableName = "customers")
public class Customer implements BaseModel{
    public Customer() {
    }

    @DatabaseField(columnName = "id_customer", generatedId = true)
    private int idCustomer;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "surname", canBeNull = false)
    private String surname;

    @DatabaseField(columnName = "phone", canBeNull = false)
    private int phone;

    @DatabaseField(columnName = "email", canBeNull = false, unique = true)
    private String email;

    @DatabaseField(columnName = "nipnumber")
    private int nipnumber;

    @DatabaseField(columnName = "companyname")
    private String companyname;

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
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

    public int getNipnumber() {
        return nipnumber;
    }

    public void setNipnumber(int nipnumber) {
        this.nipnumber = nipnumber;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }


    @Override
    public String toString() {
        return  idCustomer + ". " + name + " " + surname ;
    }

}