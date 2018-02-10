package Controllers.DataBase.dao;

import Controllers.DataBase.models.Customer;
import com.j256.ormlite.support.ConnectionSource;

import java.util.List;

/**
 * Klasa rozszerzająca klasę CommonDao. Umożliwia tworzenie zapytań do bazy związanych z obiektami typu Customer.
 */
public class CustomerDao extends CommonDao {
    /**
     * Konstruktor przyjmujący status połączenia z bazą danych. Każdy stworzony obiekt musi posiadać tą informację.
     * @param connectionSource status połączenia z bazą danych.
     */
    public CustomerDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    /**
     * Metoda zwraca wszystkie obiekty Customer z bazy danych.
     * @return lista wszystkich obiektów Customer.
     */
    public List<Customer> getCustomersList() {
        List<Customer> customers = queryForAll(Customer.class);

        return customers;
    }

}
