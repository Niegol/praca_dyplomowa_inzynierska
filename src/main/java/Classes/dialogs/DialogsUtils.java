package Classes.dialogs;

import Controllers.DataBase.Service.UserService;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import java.util.Optional;

//klasa sluzaca do wyswietlania informacji dialogowych
public class DialogsUtils {
    //metoda sluzaca do wyswietlenia odpowiednich komunikatow z opcja potwierdzenia lub odrzucenia zmiany
    //w tym konkretnym przypadku metoda sluzy do potwierdzania wyjscia z aplikacji
    public static Optional<ButtonType> confirmationDialog(){
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION); //inicjalizacja alertu potwierdzenia
        confirmationDialog.setTitle("Exit Confirmation");
        confirmationDialog.setHeaderText("Are you sure you want to exit aplication?");
        Optional<ButtonType> result = confirmationDialog.showAndWait(); //pokaz okno i poczekaj na odpowiednia reakcje
        return result;   // zwroc okno                                    czyli wcisniecie odpowiedniego przycisku
    }

    //metoda wyswietlajaca informacje o wyzucanych wyjatkach
    public static void errorDialog(String error){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);  //inicjalizacja okna zawierajacego informacje o bledach
        errorAlert.setTitle("ATTENTION!!!");
        errorAlert.setHeaderText("Something went wrong!");
        TextArea textArea = new TextArea(error);  //wypelnienie odpowiednia wiadomoscia TextArea
        textArea.setEditable(false);
        errorAlert.getDialogPane().setContent(textArea); //stowrzenie pane'a z TextFieldem i wypelnienie do
        errorAlert.showAndWait();
    }

    public static void communicat(String communicat){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATIN");
        TextArea textArea = new TextArea(communicat);
        textArea.setEditable(false);
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

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
