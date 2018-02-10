package Controllers.DataBase.Service;

import Controllers.DataBase.Converters.HistoryConverter;
import Controllers.DataBase.FXModels.HistoryFX;
import Controllers.DataBase.FXModels.RoomReservationFX;
import Controllers.DataBase.dao.HistoryDao;
import Controllers.DataBase.dbutilies.DbManager;
import Controllers.DataBase.models.History;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Klasa kontrolująca wywoływanie obiektów klasy HistoryDao. Tworzy je w przypadkach, gdy spełnione są odpowiednie
 * warunki. Posiada seterry i gettery argumetów tej klasy, dzięki którym możliwe jest operowanie na tychże argumentach.
 */
public class HistoryService {
    /**
     * Lista przechowująca wszystkie obiekty klasy HistoryFX.
     */
    private ObservableList<HistoryFX> historyFXES = FXCollections.observableArrayList();

    /**
     * Metoda pobiera wszystkie obecne w bazie danych usunięte rezerwacje (history), następnie konwertuje otrzymane z
     * bazy modele dzięki klasie HistoryCoverter() oraz zapisuje je do listy historyFXES.
     */
    public void init(){
        HistoryDao historyDao = new HistoryDao(DbManager.getConnectionSource());
        List<History> histories = historyDao.queryForAll(History.class);
        this.historyFXES.clear();
        histories.forEach(e->{
            HistoryFX historyFX = HistoryConverter.convertToHistoryFX(e);
            this.historyFXES.add(historyFX);
        });
        DbManager.closeConnectionSource();
    }


    public ObservableList<HistoryFX> getHistoryFXES() {
        return historyFXES;
    }

    public void setHistoryFXES(ObservableList<HistoryFX> historyFXES) {
        this.historyFXES = historyFXES;
    }
}
