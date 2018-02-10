package Controllers.DataBase.Converters;

import Controllers.DataBase.FXModels.ReservationFX;
import Controllers.DataBase.models.Reservation;

/**
 * Klasa umożliwiająca obustronne konwertowanie obiektów bazodanowych klasy Reservation na obiekty ReservationFX będące ich reprezentacją
 * w oknie aplikacji
 */
public class ReservationConverter {
    /**
     * Konwertuje obiekt widoku na model bazodanowy
     * @param reservationFX obiekt widoku klasy ReservationFX
     * @return model bazodanowy klasy Resevation
     */
    public static Reservation convertToReservation(ReservationFX reservationFX){
        Reservation reservation = new Reservation();
        reservation.setIdReservation(reservationFX.getId());
        reservation.setCustomer(CustomerConverter.convertToCustomer(reservationFX.getCustomerFX()));
        reservation.setAmountOfPeople(Integer.parseInt(reservationFX.getAmmountOfPeople()));
        reservation.setArrivalDate(reservationFX.getArrivalDate());
        reservation.setDepartureDate(reservationFX.getDepartureDate());
        reservation.setStartingMeal(reservationFX.getStartingMeal());
        reservation.setEndingMeal(reservationFX.getEndingMeal());
        reservation.setStatus(reservationFX.getStatus());
        reservation.setCreationDate(reservationFX.getCreationDate());
        reservation.setUser(UserConverter.convertToUser(reservationFX.getUserFX()));
        reservation.setComment(reservationFX.getComment());
        return reservation;
    }

    /**
     * Konwertuje model bazodanowy na obiekt widokowy
     * @param reservation model bazodanowy klasy Reservation
     * @return obiekt widoku klasy ResevationFX
     */
    public static ReservationFX convertToReservationFX(Reservation reservation){
        ReservationFX reservationFX = new ReservationFX();
        reservationFX.setId(reservation.getIdReservation());
        reservationFX.setCustomerFX(CustomerConverter.conertToCustomerFX(reservation.getCustomer()));
        reservationFX.setAmmountOfPeople(String.valueOf(reservation.getAmountOfPeople()));
        reservationFX.setArrivalDate(reservation.getArrivalDate());
        reservationFX.setDepartureDate(reservation.getDepartureDate());
        reservationFX.setStartingMeal(reservation.getStartingMeal());
        reservationFX.setEndingMeal(reservation.getEndingMeal());
        reservationFX.setStatus(reservation.getStatus());
        reservationFX.setCreationDate(reservation.getCreationDate());
        reservationFX.setUserFX(UserConverter.convertToUserFX(reservation.getUser()));
        reservationFX.setComment(reservation.getComment());
        return reservationFX;
    }
}
