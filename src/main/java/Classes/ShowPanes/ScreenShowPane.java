package Classes.ShowPanes;

import Classes.dialogs.DialogsUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Klasa odpowiedzialna za wyświetlanie okien aplikacji zbudowanych w kontenerze Pane.
 */
public class ScreenShowPane {
    /**
     * Konstruktor wyświetla okno odrazu po inicjalizacji
     * @param path ścieżka do pliku fxml, który ma zostać otworzony
     * @param screenTitle tytuł okna, które ma zostać wyświetlone
     * @param resizable przyjmuje wartość true/false czy okno może mieć zmienianą wielkość jeżeli podana została błędna ścieżka zostanie obsłużony wyjątek i wyświetlone zostanie komunikat
     */
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
