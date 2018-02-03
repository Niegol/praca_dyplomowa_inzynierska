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

public class HistoryService {
    private ObservableList<HistoryFX> historyFXES = FXCollections.observableArrayList();

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

    public void createHistory(History history){
        HistoryDao historyDao = new HistoryDao(DbManager.getConnectionSource());
        historyDao.createOrUpdate(history);
        DbManager.closeConnectionSource();
    }


    public ObservableList<HistoryFX> getHistoryFXES() {
        return historyFXES;
    }

    public void setHistoryFXES(ObservableList<HistoryFX> historyFXES) {
        this.historyFXES = historyFXES;
    }
}
