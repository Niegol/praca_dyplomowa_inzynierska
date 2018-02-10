package Controllers.Screens;

import Classes.ShowPanes.ScreenShowAnchorPane;
import Controllers.DataBase.Service.UserService;
import Controllers.DataBase.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa klntrolująca onko zmiany danych użytkownika.
 */
public class UserSettingsController implements Initializable{
    private static final String pathToChangePassword = "/FXMLFiles/ChangePassword.fxml";
    private static final String titleOfChangePassword = "Change Password";
    private static final Boolean resizableChangePassword = false;

    @FXML
    private Label nick;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TextField adress;

    @FXML
    private Button buttonChangePassword;

    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonSave;

    /**
     * Metoda przypisana do przycisku buttonSave. Po jego naciśnięciu nastąpi wywołanie metody zapisującej zmiany i
     * jeżeli wprowadzone formaty danych są poprawne zmiana zostanie dokonana.
     */
    public void actionSave(){
        UserService userService = new UserService();
        userService.update(phone.getText(), email.getText(), adress.getText());
        actionClose();
    }

    /**
     * Metoda zamykająca okno. Przypisana do przycisku buttonClose.
     */
    public void actionClose(){
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    /**
     * Metoda przenosi do okna zmiany hasła.
     */
    public void actionChangePassword(){
        ScreenShowAnchorPane anchorPane = new ScreenShowAnchorPane(pathToChangePassword, titleOfChangePassword, resizableChangePassword);
        actionClose();
    }


    /**
     * Metoda wykonywana od razu przyotwieraniu okna. Domyślnie wypełnia ona dane użytkownika w polach tekstowych,
     * w których może on dokonać zmiany.
     * @param location adres URL okna.
     * @param resources źródło pozwalające na uruchomienie okna z innej klasy (na przykład testującej)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserService userService = new UserService();
        User user = userService.getLoggedUser();
        nick.setText(user.getNick());
        email.setText(user.getEmail());
        phone.setText(Integer.toString(user.getPhone()));
        adress.setText(user.getAdress());
    }
}
