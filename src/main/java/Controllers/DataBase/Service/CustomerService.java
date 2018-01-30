package Controllers.DataBase.Service;

import Classes.dialogs.DialogsUtils;
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

public class CustomerService{

    private ObservableList<CustomerFX> customerList = FXCollections.observableArrayList();
    private ObjectProperty<CustomerFX> customer = new SimpleObjectProperty<>(new CustomerFX()); // obiekt podtrzymujacy obecny element w comboboxie
    private ObjectProperty<CustomerFX> customerEdit = new SimpleObjectProperty<>();



    public static List<Customer> getCustomersList(){
        CustomerDao customerDao = new CustomerDao(DbManager.getConnectionSource());
        List<Customer> arrayList = customerDao.getCustomersList();
        DbManager.closeConnectionSource();
        return arrayList;
    }

    // initialize list of cutomers in combobox in mainscreen
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

    public void saveInDB() {
        if (this.getCustomer().emailProperty().getValue().contains("@") &
                this.getCustomer().emailProperty().getValue().contains(".")) {

                try {
                    saveOrUpdate(this.getCustomer());
                } catch (NumberFormatException e){
                    DialogsUtils.errorDialog("Wrong phone or NIP format!");
                }
        }else
            DialogsUtils.communicat("Wrong email format!");

    }

    public void saveEditInDB(){
        if (this.customerEdit.get().emailProperty().getValue().contains("@") &
                this.customerEdit.get().emailProperty().getValue().contains("."))
            saveOrUpdate(this.getCustomerEdit());
        else
            DialogsUtils.communicat("Wrong email format!");

    }

    public void saveOrUpdate(CustomerFX customerFX) {
            CustomerDao customerDao = new CustomerDao(DbManager.getConnectionSource());
            Customer customerSave = CustomerConverter.convertToCustomer(customerFX);
            customerDao.createOrUpdate(customerSave);
            DbManager.closeConnectionSource();
            this.init();
    }

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
