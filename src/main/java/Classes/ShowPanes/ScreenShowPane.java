package Classes.ShowPanes;

import Classes.dialogs.DialogsUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

/*
Klasa wyswietlajaca nowe okno z glownym kontenerem
 */
public class ScreenShowPane {
    public ScreenShowPane(String path, String screenTitle, Boolean resizable) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));

        Pane pane = null;
        try {
            pane = loader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(screenTitle);
            stage.setResizable(resizable);
            stage.show();
        } catch (IOException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }

    }
}
