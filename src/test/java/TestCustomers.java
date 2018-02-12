import Controllers.DataBase.Converters.CustomerConverter;
import Controllers.DataBase.FXModels.CustomerFX;
import Controllers.DataBase.models.Customer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

/**
 * Klasa testująca dodawanie klientów z wykorzystaniem GUI
 */
public class TestCustomers extends GuiTest{
    /**
     * Ścieżka dostępu do okna klientów
     */
    private static final String path = "/FXMLFiles/AllCustomers.fxml";
    /**
     * Przykładowe imiona do testów
     */
    private final String[] names = new String[]{"Maciej", "Karol"};
    /**
     * Przykładowe nazwiska do testów
     */
    private final String[] surnames = new String[]{"Kowal", "Kowalska"};
    /**
     * Przykładowe adresy email do testów
     */
    private final String[] emails = new String[]{"drama@wp.pl", "karol@gmail.com"};
    /**
     * Przykładowe numery telefonu do testów
     */
    private final Integer[] phones = new Integer[]{721456234, null};

    /**
     * Metoda otwierająca okno testowe
     * @return obiekt reprezentujący kontroler okna
     */
    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource(path));
            return parent;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Metoda testująca dodanie klienta. Wypełnia pola tekstowe poprawnymi danymi klienta zaprezentowanymi jako pierwsze
     * elementy tablic. Dodanie go do bazy powienno być zatem możliwe.
     */
    @Test
    public void addCustomerTestPossible() {

        TextField name = find("#name");          //
        TextField surname = find("#surname");    //  pola tekstowe, do których wprowadzane będą dane klienta
        TextField email = find("#email");        //
        TextField phone = find("#phone");        //
        TableView<CustomerFX> customers = find("#tableCustomers");     // tabela przechowująca dane klientów
        Button buttonAdd = find("#buttonAdd");    // przycisk dodający klienta

        name.setText(names[0]);                     //
        surname.setText(surnames[0]);               //  wypełnienie pól tekstowych pierwszymi elementami tablic
        email.setText(emails[0]);                   //
        phone.setText(String.valueOf(phones[0]));   //

        buttonAdd.fire();       // wciśnięcie przycisku dodającego

        Customer customerAfter = CustomerConverter.convertToCustomer   // zczytanie danych ostatniego klienta z tablicy
                (customers.getItems().get(customers.getItems().size()-1));

        assertEquals(customerAfter.getName(), names[0]);            //
        assertEquals(customerAfter.getSurname(), surnames[0]);      //  porównanie otrzymanych wyników z oczekiwanymi.
        assertEquals(customerAfter.getEmail(), emails[0]);          //  Metody zwracają prawdę jeżeli tak właśnie jest
        assertEquals(customerAfter.getPhone(), (int)phones[0]);     //
    }

    /**
     * Metoda testująca blokadę przycisku. Wypełnia pola tekstowe niepoprawną listą danych. W liście brakuje numeru
     * telefonu. Z założeń wynika, że przycisk dodający powinien być zablokowany.
     */
    @Test
    public void addCustomerTestImpossible(){
        TextField name = find("#name");         //
        TextField surname = find("#surname");   //  pola tekstowe, do których będą wprowadzana dane
        TextField email = find("#email");       //
        TextField phone = find("#phone");       //

        Button buttonAdd = find("#buttonAdd");  //  przycisk dodający klienta

        name.setText(names[1]);                     //
        surname.setText(surnames[1]);               //  wypełnienie pól tekstowych drugimi elementami tablic
        email.setText(emails[1]);                   //
        phone.setText(String.valueOf(phones[1]));   //

        assertTrue(buttonAdd.isDisable());      // funkcja sprawdzająca czy przycisk jest zablokowany
                                                // zwraca prawdę jeżeli tak właśnie jest
    }
}
