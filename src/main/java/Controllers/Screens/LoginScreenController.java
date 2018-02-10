package Controllers.Screens;

import Classes.ShowPanes.ScreenShowBorderPane;
import Controllers.DataBase.Service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa kontrolująca okno odpowiedzialne za logowanie do aplikacji.
 */
public class LoginScreenController implements Initializable {
    private static final String path = "/FXMLFiles/MainScreen.fxml";
    private static final String screenTitle = "Main Screen";
    private static final Boolean resizable = true;

    @FXML
    private Pane logscreen;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Label wrongData;

    /**
     * Metoda odpowiedzialna za logowanie do systemu. Przypisana do przycisku login. Jezeli po wprowadzeniu danych
     * klasa UserService pozwoli na zalogowanie się (poprawne dane logowania) otwarte zostanie główne okno aplikacji,
     * w przeciwnym razie wyświetloma zostanie informacja, że któreś z pól posiada złą wartość.
     */
    @FXML
    private void actionLogin(){
        wrongData.setVisible(false);
        UserService userService = new UserService();
        if(userService.logIn(userName.getText(), password.getText())==true){
            ScreenShowBorderPane screenShowBorderPane = new ScreenShowBorderPane(path, screenTitle, resizable);
            close();
        }
        else {
            wrongData.setVisible(true);
        }

    }

    /**
     * Metoda zamykająca okno logowania.
     */
    @FXML
    public void close(){
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
    }

    /**
     * Metoda uruchamiana przy starcie aplikacji. Binduje ona pola tekstowe z przyciskiem. Do czasu zapełnienia dwóch
     * pól przycisk logowania będzie niedostępny.
     * @param location adres URL okna.
     * @param resources żródło.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.login.disableProperty().bind(this.userName.textProperty().isEmpty().or(
                this.password.textProperty().isEmpty()
        ));
    }

    /**
     * Metoda umożliwiająca zalogowanie się do systemu po wciśnięciu klawisza Enter.
     * @param keyEvent informacja o wciśniętym klawiszu.
     */
    public void enterPressed(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if (code == KeyCode.ENTER)
            this.login.fire();
    }
}
