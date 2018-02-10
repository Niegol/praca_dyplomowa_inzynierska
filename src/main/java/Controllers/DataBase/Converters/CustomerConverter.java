package Controllers.DataBase.Converters;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.FXModels.CustomerFX;
import Controllers.DataBase.models.Customer;
import com.j256.ormlite.stmt.query.In;

/**
 * Klasa umożliwiająca obustronne konwertowanie obiektów bazodanowych klasy Customer na obiekty CustomerFX będące ich reprezentacją
 * w oknie aplikacji
 */
public class CustomerConverter {

    /**
     * Konwersja z obiektu CustomerFX na Customer
     * @param customerFX model widoku klasy CustomerFX
     * @return model bazodanowy klasy Customer
     */
    public static Customer convertToCustomer(CustomerFX customerFX){
        Customer customer = new Customer();
        customer.setIdCustomer(customerFX.getId());
        customer.setName(customerFX.getName());
        customer.setSurname(customerFX.getSurname());
        customer.setEmail(customerFX.getEmail());

        customer.setPhone(Integer.parseInt(customerFX.getPhone()));
        if(customerFX.getNip().isEmpty())
            customer.setNipnumber(0);
        else
            customer.setNipnumber(Integer.parseInt(customerFX.getNip()));

        customer.setCompanyname(customerFX.getCompany());
        return customer;
    }

    /**
     * Konwersja z obiektu Customer na CustomerFX
     * @param customer model bazodanowy klasy Customer
     * @return model widoku klasy CustomerFX
     */
    public static CustomerFX conertToCustomerFX(Customer customer){
        CustomerFX customerFX = new CustomerFX();
        customerFX.setId(customer.getIdCustomer());
        customerFX.setName(customer.getName());
        customerFX.setSurname(customer.getSurname());
        customerFX.setPhone(Integer.toString(customer.getPhone()));
        customerFX.setEmail(customer.getEmail());
        customerFX.setNip(Integer.toString(customer.getNipnumber()));
        customerFX.setCompany(customer.getCompanyname());
        return customerFX;
    }
}
