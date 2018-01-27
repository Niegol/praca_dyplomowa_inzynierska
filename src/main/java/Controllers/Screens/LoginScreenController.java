package Controllers.Screens;

import Classes.ShowPanes.ScreenShowBorderPane;
import Controllers.DataBase.Service.UserService;
import Controllers.DataBase.dbutilies.DbMenager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

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
    public void close(){
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
