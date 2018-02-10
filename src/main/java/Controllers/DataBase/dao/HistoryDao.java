package Controllers.DataBase.dao;

import com.j256.ormlite.support.ConnectionSource;

/**
 * Klasa odpowiadająca za kreowanie zapytań związanych z obiektami History. Rozszerza klasę CommonDao
 */
public class HistoryDao extends CommonDao {
    /**
     * Konstruktor przyjmujący status połączenia z bazą danych. Każdy stworzony obiekt musi posiadać tą informację.
     * @param connectionSource status połączenia z bazą danych.
     */
    public HistoryDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

}
