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
import static junit.framework.TestCase.assertEquals;


public class TestCustomers extends GuiTest{
    private static final String path = "/FXMLFiles/AllCustomers.fxml";
    private static final String n = "Maciej";
    private static final String s = "Niegolewski";
    private static final String e = "maciek.niegolewski@wddw.pl";
    private static final int ph = 789654321;


    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource(path));
             return parent;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return parent;
    }

    @Test
    public void addCustomerTest() {
        TextField name = find("#name");
        TextField surname = find("#surname");
        TextField email = find("#email");
        TextField phone = find("#phone");
        TableView<CustomerFX> customers = find("#tableCustomers");

        name.setText(n);
        surname.setText(s);
        email.setText(e);
        phone.setText(String.valueOf(ph));


        Button buttonAdd = find("#buttonAdd");
        buttonAdd.fire();


        Customer customerAfter = CustomerConverter.convertToCustomer(
                customers.getItems().get(customers.getItems().size()-1));


        assertEquals(customerAfter.getEmail(), e);


    }


}