package Classes;

import Classes.ShowPanes.ScreenShowPane;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main  extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
        ScreenShowPane screenShowPane = new ScreenShowPane("/FXMLFiles/LoginScreen.fxml", "Login Screen", false);

    }
}
