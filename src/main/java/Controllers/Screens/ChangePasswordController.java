package Controllers.Screens;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.Service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa odpowiedzialna kontrolę okna umożliwiającego zmianę hasła użytkownika.
 */
public class ChangePasswordController implements Initializable{
    @FXML
    private Label nick;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button buttonSavePassword;

    @FXML
    private Button buttonClose;

    private UserService userService;

    /**
     * Metoda bindująca elementy okna. Aby odblokować przycisk zatwierdzania należy zapełnić wszystkie 3 pola odpowiedzialne
     * za zmianę (stare hasło, nowe, potwierdzenie nowego) oraz podane nowe hasła muszą być takie same. W przypadku
     * Podania niepoprawnego starego hasła akcja zmiany hasła będzie niemożliwa.
     */
    public void initBindings(){
        this.userService.userFXNewPassObjectPropertyProperty().get().passwordProperty().bind(this.newPassword.textProperty());
        this.buttonSavePassword.disableProperty().bind(
                this.oldPassword.textProperty().isEmpty().
                or(this.newPassword.textProperty().isEmpty()).or(this.confirmPassword.textProperty().isEmpty()).
                or(this.newPassword.textProperty().isNotEqualTo(this.confirmPassword.textProperty()))
        );
    }

    /**
     * Akcja przypisana do przycisku odpowiedzialna za wywołanie metody zmiany hasła.
     */
    public void actionSavePassword(){
        this.userService.saveUserInDB(this.oldPassword.getText(),
                this.userService.getUserFXObjectProperty().getPassword());
        this.close();
    }

    /**
     * Metoda zamykająca okno aplikacji
     */
    public void close(){
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    /**
     * Metoda uruchamiana od razu po otwarciu tego okna. Inicjuje ona metodę initBindings() oraz pobiera dane zalogowanego
     * użytkownika
     * @param location adres URL do pliku fxml.
     * @param resources źródło.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userService = new UserService();
        userService.init();
        nick.setText(userService.getUserNick());
        initBindings();
    }


}
