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

    public void actionSave(){
        UserService userService = new UserService();
        userService.update(phone.getText(), email.getText(), adress.getText());
        actionClose();
    }

    public void actionClose(){
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    public void actionChangePassword(){
        ScreenShowAnchorPane anchorPane = new ScreenShowAnchorPane(pathToChangePassword, titleOfChangePassword, resizableChangePassword);
        actionClose();
    }


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
