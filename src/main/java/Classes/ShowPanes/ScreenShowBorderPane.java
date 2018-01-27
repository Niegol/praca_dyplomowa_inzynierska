package Classes.ShowPanes;


import Classes.dialogs.DialogsUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/*
Klasa wyswietlajaca nowe okno z glownym kontenerem BorderPane
 */
public class ScreenShowBorderPane {
    public ScreenShowBorderPane(String path, String screenTitle, Boolean resizable){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));

        BorderPane borderPane = null;
        try {
            borderPane = loader.load();
            Scene scene = new Scene(borderPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(screenTitle);
            stage.setResizable(resizable);
            stage.setFullScreen(false);
            stage.show();
        } catch (IOException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }


}
