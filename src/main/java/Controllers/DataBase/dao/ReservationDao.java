package Controllers.DataBase.dao;

import Controllers.DataBase.models.Reservation;
import com.j256.ormlite.support.ConnectionSource;

import java.util.List;

/**
 * Klasa rozszerzająca klasę CommonDao, umożliwiająca tworzenie kwerend związanych z obiektami Reservation.
 */
public class ReservationDao extends CommonDao {
    /**
     * Konstruktor przyjmujący status połączenia z bazą danych. Każdy stworzony obiekt musi posiadać tą informację.
     * @param connectionSource status połączenia z bazą danych.
     */
    public ReservationDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }


}
