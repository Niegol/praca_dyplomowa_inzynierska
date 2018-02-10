package Classes.dialogs;

import Controllers.DataBase.Service.UserService;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import java.util.Optional;

/**
 * Jest to klasa odpowiedzialna za wyswietlanie informacji w formie okien dialogowych
 */
public class DialogsUtils {
    /**
     * Metoda statyczna służąca do wyswietlenia odpowiednich komunikatow z opcja potwierdzenia lub odrzucenia zmiany.
     * @return zwraca wiadomość który z przycisków został wciśnięty
     */
    public static Optional<ButtonType> confirmationDialog(){
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION); //inicjalizacja alertu potwierdzenia
        confirmationDialog.setTitle("Exit Confirmation");
        confirmationDialog.setHeaderText("Are you sure you want to exit aplication?");
        Optional<ButtonType> result = confirmationDialog.showAndWait(); //pokaz okno i poczekaj na odpowiednia reakcje
        return result;   // zwroc okno                                    czyli wcisniecie odpowiedniego przycisku
    }

    //

    /**
     * Wyswietlaja informacje o wyzucanych wyjatkach. Po wyświetleniu czeka na potwierdze klaiwszem "OK"
     * @param error informacja o błędzie
     */
    public static void errorDialog(String error){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);  //inicjalizacja okna zawierajacego informacje o bledach
        errorAlert.setTitle("ATTENTION!!!");
        errorAlert.setHeaderText("Something went wrong!");
        TextArea textArea = new TextArea(error);  //wypelnienie odpowiednia wiadomoscia TextArea
        textArea.setEditable(false);
        errorAlert.getDialogPane().setContent(textArea); //stowrzenie pane'a z TextFieldem i wypelnienie do
        errorAlert.showAndWait();
    }

    /**
     * Wyświetla na ekranie komunikat
     * @param communicat treść komunikatu
     */
    public static void communicat(String communicat){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATIN");
        TextArea textArea = new TextArea(communicat);
        textArea.setEditable(false);
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }


    /**
     * Odpowiada za komunikat zamykający okno, przyjmuje wartość @confirmationDialog()
     */
    public static void closeApplication(){
        Optional<ButtonType> result = confirmationDialog();
        if (result.get()== ButtonType.OK){
            UserService userService = new UserService();
            userService.logOut();
            Platform.exit();
            System.exit(0);
        }
    }

}
