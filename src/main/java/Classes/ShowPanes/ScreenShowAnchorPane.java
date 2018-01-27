package Classes.ShowPanes;

import Classes.dialogs.DialogsUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class ScreenShowAnchorPane {

    public ScreenShowAnchorPane(String path, String screenTitle, Boolean resizable){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));

        AnchorPane anchorPane = null;
        try {
            anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
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
