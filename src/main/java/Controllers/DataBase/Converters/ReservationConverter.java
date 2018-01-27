package Controllers.DataBase.Converters;

import Controllers.DataBase.FXModels.ReservationFX;
import Controllers.DataBase.models.Reservation;

public class ReservationConverter {
    public static Reservation convertToReservation(ReservationFX reservationFX){
        Reservation reservation = new Reservation();
        reservation.setIdResevation(reservationFX.getId());
        reservation.setCustomer(CustomerConverter.convertToCustomer(reservationFX.getCustomerFX()));
        reservation.setAmpuntOfPeople(Integer.parseInt(reservationFX.getAmmountOfPeople()));
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

    public static ReservationFX convertToReservationFX(Reservation reservation){
        ReservationFX reservationFX = new ReservationFX();
        reservationFX.setId(reservation.getIdResevation());
        reservationFX.setCustomerFX(CustomerConverter.conertToCustomerFX(reservation.getCustomer()));
        reservationFX.setAmmountOfPeople(String.valueOf(reservation.getAmpuntOfPeople()));
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
