package Controllers.DataBase.Service;

import Controllers.DataBase.Converters.CustomerConverter;
import Controllers.DataBase.FXModels.CustomerFX;
import Controllers.DataBase.dao.CustomerDao;
import Controllers.DataBase.dbutilies.DbManager;
import Controllers.DataBase.models.Customer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

/**
 * Klasa kontrolująca wywoływanie obiektów klasy CustomerDao. Tworzy je w przypadkach, gdy spełnione są odpowiednie
 * warunki. Posiada seterry i gettery argumetów tej klasy, dzięki którym możliwe jest operowanie na tychże argumentach.
 */
public class CustomerService{
    /**
     * Lista dzięki której możliwe jest wyświetlanie wszystkich klientów znajdujących się w bazie danych.
     */
    private ObservableList<CustomerFX> customerList = FXCollections.observableArrayList();
    /**
     * Obiekt przechowujący informację o danych klienta (Customer), które są wprowadzane w oknie kontrolowanym przez
     * AllCustomersController.
     */
    private ObjectProperty<CustomerFX> customer = new SimpleObjectProperty<>(new CustomerFX());
    /**
     * Obiekt przechowujący informację o kliencie (Customer), który ma zostać usunięty, albo edytowaney.
     */
    private ObjectProperty<CustomerFX> customerEdit = new SimpleObjectProperty<>();




    /**
     * Metoda pobiera wszystkich obecnych w bazie danych klientów (Customer), następnie konwertuje otrzymane z bazy
     * modele dzięki klasie CustomerCoverter() oraz zapisuje je do listy customerList.
     */
    public void init(){
        CustomerDao customerDao = new CustomerDao(DbManager.getConnectionSource());
        List<Customer> customers = customerDao.queryForAll(Customer.class);
        this.customerList.clear();
        customers.forEach(customer ->{
            CustomerFX customerFX = CustomerConverter.conertToCustomerFX(customer);
            this.customerList.add(customerFX);
        });
        DbManager.closeConnectionSource();
    }

    /**
     * Metoda zapisuje stworzonego klienta (Customer) w bazie danych, jednak jeżeli która kolwiek dana będzie niezgodna
     * z formatem, klient nie zostanie zapisany.Jeżeli akcja jest możliwa do wykonania, wówczas wykonana zostaje
     * metoda saveOrUpdate().
     */
    public void saveInDB() {
        if (Service.isEmailCorrect(this.customer.get().emailProperty().getValue()) &
                Service.isNameCorrect(this.customer.get().nameProperty().getValue()) &
                Service.isSurnameCorrect(this.customer.get().surnameProperty().getValue()) &
                Service.isPhoneCorrect(this.customer.get().phoneProperty().getValue()) )
            saveOrUpdate(this.getCustomer());

    }

    /**
     * Metoda edytuje istniejącego w bazie klienta (Customer), jednak jeżeli która kolwiek dana będzie niezgodna
     * z formatem, klient nie zostanie edytowany. Jeżeli akcja jest możliwa do wykonania, wówczas wykonana zostaje
     * metoda saveOrUpdate().
     */
    public void saveEditInDB(){
        if (Service.isEmailCorrect(this.customerEdit.get().emailProperty().getValue()) &
                Service.isNameCorrect(this.customerEdit.get().nameProperty().getValue()) &
                Service.isSurnameCorrect(this.customerEdit.get().surnameProperty().getValue()) &
                Service.isPhoneCorrect(this.customerEdit.get().phoneProperty().getValue()) )
            saveOrUpdate(this.getCustomerEdit());

    }

    /**
     * Metoda pozwalająca zapisać lub edytować klienta (Customer) w bazie danych, jednak jest wywoływana przez metody,
     * które kontrolują jej uruchomienie. Metoda przyjmuje obiekt widokowy CustomerFX, tworzy obiekt typu CustomerDao
     * (przyjmujący połączenie do bazy danych dzięki stworzonej klasie DbManager), konwertuje CustomerFX na model
     * Customer i dzięki stworzonemu CustomerDao zapisuje klienta do bazy danych. Po dodaniu następuje zamknięcie
     * połączenie do baay danych odpowiadający encji w bazie danych oraz póżniej odpowiednio go zapisuje lub edytuje.
     * Po akcji następuje wywołanie metody init, któraaktualizuje wprowadzone zmiany.
     * @param customerFX obiekt wi
     */
    private void saveOrUpdate(CustomerFX customerFX) {
            CustomerDao customerDao = new CustomerDao(DbManager.getConnectionSource());
            Customer customerSave = CustomerConverter.convertToCustomer(customerFX);
            customerDao.createOrUpdate(customerSave);
            DbManager.closeConnectionSource();
            this.init();
    }

    /**
     * Metoda usuwająca klienta z bazydanych. Tworzony jest obiekt klasy CustomerDao przyjmujący połączenie z bazą
     * danych, następuje wywołanie na nim metody deleteById() przyjmującej jako argument customerEdit będoący argumentem
     * klasy CustomerService.
     */
    public void deleteInDB(){
        CustomerDao customerDao = new CustomerDao(DbManager.getConnectionSource());
        customerDao.deleteById(Customer.class, this.getCustomerEdit().getId());
        DbManager.closeConnectionSource();
        this.init();
    }

    public ObservableList<CustomerFX> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ObservableList<CustomerFX> customerList) {
        this.customerList = customerList;
    }

    public CustomerFX getCustomer() {
        return customer.get();
    }

    public ObjectProperty<CustomerFX> customerProperty() {
        return customer;
    }

    public void setCustomer(CustomerFX customer) {
        this.customer.set(customer);
    }

    public CustomerFX getCustomerEdit() {
        return customerEdit.get();
    }

    public ObjectProperty<CustomerFX> customerEditProperty() {
        return customerEdit;
    }

    public void setCustomerEdit(CustomerFX customerEdit) {
        this.customerEdit.set(customerEdit);
    }
}
