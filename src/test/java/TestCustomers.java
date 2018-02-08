import Controllers.DataBase.Converters.CustomerConverter;
import Controllers.DataBase.FXModels.CustomerFX;
import Controllers.DataBase.models.Customer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

public class TestCustomers extends GuiTest{
    private static final String path = "/FXMLFiles/AllCustomers.fxml";
    private final String[] names = new String[]{"Maciej", "Karol"};
    private final String[] surnames = new String[]{"Kowal", ""};
    private final String[] emails = new String[]{"drama@wp.pl", "karol@gmail.com"};
    private final Integer[] phones = new Integer[]{721456234, null};
    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource(path));         // symulacja otwarcia okna
            return parent;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Test
    public void addCustomerTestPossible() {
        TextField name = find("#name");                // znajdowanie elementow na ktorych bedzie sie operowac
        TextField surname = find("#surname");
        TextField email = find("#email");
        TextField phone = find("#phone");
        TableView<CustomerFX> customers = find("#tableCustomers");          //tabela wyswietlajaca dane kientow
        Button buttonAdd = find("#buttonAdd");

        name.setText(names[0]);                        // wypelnienie pol zadanymi wartosciami
        surname.setText(surnames[0]);
        email.setText(emails[0]);
        phone.setText(String.valueOf(phones[0]));

        buttonAdd.fire();

        Customer customerAfter = CustomerConverter.convertToCustomer           // pobranie ostatniego elementu tabeli
                (customers.getItems().get(customers.getItems().size()-1));

        assertEquals(customerAfter.getName(), names[0]);
        assertEquals(customerAfter.getSurname(), surnames[0]);
        assertEquals(customerAfter.getEmail(), emails[0]);
        assertEquals(customerAfter.getPhone(), (int)phones[0]);
    }
    @Test
    public void addCustomerTestImpossible(){
        TextField name = find("#name");              // znajdowanie elementow na ktorych bedzie sie operowac
        TextField surname = find("#surname");
        TextField email = find("#email");
        TextField phone = find("#phone");
        Button buttonAdd = find("#buttonAdd");

        name.setText(names[1]);                           // wypelnienie pol zadanymi wartosciami
        surname.setText(surnames[1]);
        email.setText(emails[1]);
        phone.setText(String.valueOf(phones[1]));

        assertTrue(buttonAdd.isDisable());
    }
}
