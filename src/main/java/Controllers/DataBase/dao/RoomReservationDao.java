package Controllers.DataBase.dao;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.models.*;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa rozszerzająca klasę CommonDao. Umożliwia tworzenie kwerdend odpowiedzialnych za modele klasy RoomReservation.
 * Można powiedzieć, że jest to główna klasa programu, gdyż zawiera w sobie najważniejsze informacje czyli do jakiej
 * rezerwacji przypisany jest dany pokój, klient.
 */
public class RoomReservationDao extends CommonDao{
    /**
     * Konstruktor przyjmujący status połączenia z bazą danych. Każdy stworzony obiekt musi posiadać tą informację.
     * @param connectionSource status połączenia z bazą danych.
     */
    public RoomReservationDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    /**
     * Metoda zwracająca wszystkie pokoje przypisane do danej rezerwacji, poprzez zadanie w argumencie modelu
     * Reservation, do którego mają one zostać przypisane. W przypadku błędnego nawiązania połączenia z bazą danych
     * wyświetlony zostanie odpowiedni komunikat.
     * @param reservation parametr, do którego przypisane są pokoje.
     * @return lista obiektów RoomReservation zawierająca wszystkie pokoje przypisane do zadanej rezerwacji
     */
    public List<RoomReservation> findByIdReservation(Reservation reservation){
        List<RoomReservation> roomReservations = new ArrayList<>();

        QueryBuilder<RoomReservation, Integer> roomReservationIntegerQB = getQueryBuilder(RoomReservation.class);
        try {
            roomReservationIntegerQB.where().eq("id_reservation", reservation);
            PreparedQuery<RoomReservation> preparedQuery = roomReservationIntegerQB.prepare();
            roomReservations = getDao(RoomReservation.class).query(preparedQuery);
            return roomReservations;
        } catch (SQLException e) {
            DialogsUtils.errorDialog("Can't find rooms reservation!");
            return roomReservations;
        }
    }

    /**
     * Metoda zwracająca obiekty RoomReservations, na podanym przedziale czasowym. Każdy obiekt posiada przypisany do
     * niego konkretny pokój (Room) oraz Reserwację. Ta z kolei posiada klienta (Customer), który dokonał rezerwacji.
     * W przypadku błędnego połączenia z bazą danych wyświetlone zostanie odpowiednie okno dialogowe z informacją o
     * błędzie.
     * @param low data, od której ma nastąpić wysukiwanie.
     * @param high data, do której ma nastąpić wyszukiwanie.
     * @return lista wszystkich RoomReserwations, czyli wszystkich rezerwacji i przypisanymi do nich pokojami na zadanym
     * terminie
     */
    public List<RoomReservation> informationsAboutReservations(LocalDate low, LocalDate high){
        List<RoomReservation> roomReservations = new ArrayList<>();
        try {
            QueryBuilder<RoomReservation, Integer> roomReservationQB = getQueryBuilder(RoomReservation.class);


            QueryBuilder<Room, Integer> roomQB = getQueryBuilder(Room.class);
            roomQB.where().eq("floor", 0).and().eq("pavilon", "A");
            QueryBuilder<Customer, Integer> customerQB = getQueryBuilder(Customer.class);
            QueryBuilder<Reservation, Integer> reservationQB = getQueryBuilder(Reservation.class);
            QueryBuilder<Reservation, Integer> reservationAr = getQueryBuilder(Reservation.class);
            QueryBuilder<Reservation, Integer> reservationDp = getQueryBuilder(Reservation.class);




            reservationAr.where().le("arrival_date", low).and().ge("departure_date", low);
            reservationDp.where().le("arrival_date", high).and().ge("departure_date", high);

            reservationQB.where().between("arrival_date", low, high)
                    .or().between("departure_date", low, high)
                    .or().le("arrival_date", low).or().ge("departure_date", low)
                    .or().le("arrival_date", high).or().ge("departure_date", high);

//            reservationQB.where().le("arrival_date", low).and().ge("departure_date", low);
//            reservationQB.where().le("arrival_date", high).and().ge("departure_date", high);
//
//            reservationQB.join(reservationAr);
//            reservationQB.join(reservationDp);

            reservationQB.join(customerQB);
            roomReservationQB.join(roomQB).join(reservationQB);

            PreparedQuery<RoomReservation> preparedQuery = roomReservationQB.prepare();
            roomReservations = getDao(RoomReservation.class).query(preparedQuery);
            return roomReservations;
        }catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
            return roomReservations;
        }
    }

//    public boolean isReserved(LocalDate low, LocalDate high, int id){
//        List<RoomReservation> roomReservations = new ArrayList<>();
//        try {
//            QueryBuilder<RoomReservation, Integer> roomReservationQB = getQueryBuilder(RoomReservation.class);
//            QueryBuilder<Room, Integer> roomQB = getQueryBuilder(Room.class);
//            roomQB.where().eq("floor", 0).and().eq("pavilon", "A")
//                    .and().eq("id_room", id);
//            QueryBuilder<Customer, Integer> customerQB = getQueryBuilder(Customer.class);
//            QueryBuilder<Reservation, Integer> reservationQB = getQueryBuilder(Reservation.class);
//
//            reservationQB.where().between("arrival_date", low, high)
//                    .or().between("departure_date", low, high);
//
//            reservationQB.join(customerQB);
//            roomReservationQB.join(roomQB).join(reservationQB);
//
//            PreparedQuery<RoomReservation> preparedQuery = roomReservationQB.prepare();
//            roomReservations = getDao(RoomReservation.class).query(preparedQuery);
//
//            if(roomReservations.isEmpty())
//                return false;
//            else
//                return true;
//
//        }catch (SQLException e) {
//            DialogsUtils.errorDialog(e.getMessage());
//            return true;
//        }
//    }
}
