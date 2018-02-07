//
//import Controllers.DataBase.Converters.CustomerConverter;
//import Controllers.DataBase.models.Customer;
//import Controllers.Screens.AllCustomersController;
//
//import de.saxsys.javafx.test.JfxRunner;
//
//import javafx.application.Platform;
//import javafx.embed.swing.JFXPanel;
//import javafx.fxml.FXMLLoader;
//
//import javafx.stage.Stage;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//
//import java.io.IOException;
//
//
//@RunWith(JfxRunner.class)
//public class AllCustomersControllerTest {
//
//    private final String path = "/FXMLFiles/AllCustomers.fxml";
//    private final String name = "Maciej";
//    private final String surname = "Niegolewski";
//    private final String email = "maciek.niegolewski@wddw.pl";
//    private final int phone = 789654321;
//
//
//    @Test
//    public void addCustomer() throws IOException, InterruptedException {
//
//        Thread thread = new Thread(new Runnable() {
//
//           @Override
//           public void run() {
//               new JFXPanel(); // Initializes the JavaFx Platform
//               Platform.runLater(new Runnable() {
//
//               @Override
//               public void run() {
//                   try {
//
//                       FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(path));
//                       fxmlLoader.load();
//
//                       AllCustomersController allCustomersController = fxmlLoader.getController();
//                       allCustomersController.setName(name);
//                       allCustomersController.setSurname(surname);
//                       allCustomersController.setEmail(email);
//                       allCustomersController.setPhone(String.valueOf(phone));
//                       int i = allCustomersController.getCustomerService().getCustomerList().size();
//                       allCustomersController.buttonAdd.fire();
//                       Customer customer = CustomerConverter.convertToCustomer(allCustomersController
//                               .getCustomerService().getCustomerList().get(i));
//
//
//                       Stage stage = (Stage) allCustomersController.buttonAdd.getScene().getWindow();
//                       stage.close();
//                       Assert.assertEquals(customer.getName(), name);
//                       Assert.assertEquals(customer.getSurname(), surname);
//                       Assert.assertEquals(customer.getEmail(), email);
//                       Assert.assertEquals(customer.getPhone(), phone);
//
//
//
//                   } catch (Exception e) {
//                       e.printStackTrace();
//                   }
//               }
//               });
//           }
//        });
//        thread.start();// Initialize the thread
//        Thread.sleep(10000);
//    }
//
//
//}
//
//
//
