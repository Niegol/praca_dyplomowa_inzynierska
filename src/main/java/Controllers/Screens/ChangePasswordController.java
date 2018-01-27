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

    public void initBindings(){
        this.userService.userFXNewPassObjectPropertyProperty().get().passwordProperty().bind(this.newPassword.textProperty());
        this.buttonSavePassword.disableProperty().bind(
                this.oldPassword.textProperty().isEmpty().
                or(this.newPassword.textProperty().isEmpty()).or(this.confirmPassword.textProperty().isEmpty()).
                or(this.newPassword.textProperty().isNotEqualTo(this.confirmPassword.textProperty()))
        );
    }

    public void actionSavePassword(){
        this.userService.saveUserInDB(this.oldPassword.getText(),
                this.userService.getUserFXObjectProperty().getPassword());
        this.close();
    }

    public void close(){
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userService = new UserService();
        userService.init();
        nick.setText(userService.getUserNick());
        initBindings();
    }


}
