package Controllers.DataBase.dao;

import Controllers.DataBase.models.Customer;
import com.j256.ormlite.support.ConnectionSource;

import java.util.List;

public class CustomerDao extends CommonDao {
    public CustomerDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public List<Customer> getCustomersList() {
        List<Customer> customers = queryForAll(Customer.class);

        return customers;
    }

    public void update(Customer customer) {
        createOrUpdate(customer);
    }


}
