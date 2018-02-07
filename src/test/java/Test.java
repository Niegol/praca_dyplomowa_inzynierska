import Controllers.DataBase.Converters.CustomerConverter;
import Controllers.DataBase.models.Customer;
import Controllers.Screens.AllCustomersController;
import de.saxsys.javafx.test.JfxRunner;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(JfxRunner.class)
public class Test {
    private static final String path = "/FXMLFiles/AllCustomers.fxml";
    private static final String name = "Maciej";
    private static final String surname = "Niegolewski";
    private static final String email = "maciek.niegolewski@wddw.pl";
    private static final int phone = 789654321;

    @org.junit.Test
    public void addCustomer() throws IOException {



        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(path));
        fxmlLoader.load();

        AllCustomersController allCustomersController = fxmlLoader.getController();
        allCustomersController.setName(name);
        allCustomersController.setSurname(surname);
        allCustomersController.setEmail(email);
        allCustomersController.setPhone(String.valueOf(phone));
        int i = allCustomersController.getCustomerService().getCustomerList().size();
        allCustomersController.buttonAdd.fire();
        Customer customer = CustomerConverter.convertToCustomer(allCustomersController
                               .getCustomerService().getCustomerList().get(i));

        Assert.assertEquals(customer.getName(), name);
        Assert.assertEquals(customer.getSurname(), surname);
        Assert.assertEquals(customer.getEmail(), email);
        Assert.assertEquals(customer.getPhone(), phone);


    }

}
