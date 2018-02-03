package Controllers.DataBase.Converters;

import Controllers.DataBase.FXModels.HistoryFX;
import Controllers.DataBase.models.History;

public class HistoryConverter {
    public static History convertToHistory(HistoryFX historyFX){
        History history = new History();
        history.setIdHistory(historyFX.getId());
        history.setCustomerSurname(historyFX.getCustomerSurname());
        history.setArrival(historyFX.getArrivalDate());
        history.setDeparture(historyFX.getDepartureDate());
        history.setUser(UserConverter.convertToUser(historyFX.getUserFX()));
        history.setAmountPeople(Integer.parseInt(historyFX.getAmountPeople()));
        history.setChangingDate(historyFX.getChangingDate());
        return history;
    }

    public static HistoryFX convertToHistoryFX(History history){
        HistoryFX historyFX = new HistoryFX();
        historyFX.setId(history.getIdHistory());
        historyFX.setCustomerSurname(history.getCustomerSurname());
        historyFX.setArrivalDate(history.getArrival());
        historyFX.setDepartureDate(history.getDeparture());
        historyFX.setUserFX(UserConverter.convertToUserFX(history.getUser()));
        historyFX.setAmountPeople(String.valueOf(history.getAmountPeople()));
        historyFX.setChangingDate(history.getChangingDate());
        return historyFX;
    }
}
