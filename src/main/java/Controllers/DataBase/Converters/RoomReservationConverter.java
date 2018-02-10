package Controllers.DataBase.Converters;

import Controllers.DataBase.FXModels.RoomReservationFX;
import Controllers.DataBase.models.RoomReservation;

/**
 * Klasa umożliwiająca obustronne konwertowanie obiektów bazodanowych klasy RoomReservation na obiekty RoomeservationFX będące ich reprezentacją
 * w oknie aplikacji
 */
public class RoomReservationConverter {
    /**
     * Konwertuje obiekt widokow na model bazodanowy
     * @param roomReservationFX model widoku klasy RoomReservationFX
     * @return obiekt bazodanowy klasy RoomReservation
     */
    public static RoomReservation convertToRoomReservation(RoomReservationFX roomReservationFX){
        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setId(roomReservationFX.getId());
        roomReservation.setReservation(ReservationConverter.convertToReservation(roomReservationFX.getReservationFX()));
        roomReservation.setRoom(RoomConverter.convertToRoom(roomReservationFX.getRoomFX()));
        return  roomReservation;
    }

    /**
     * Konwertuje model bazodanowy na obiekt widokowy
     * @param roomReservation model bazodanowy klasy Roomreservation
     * @return obiekt widoku RoomReservationFX
     */
    public static RoomReservationFX convertToRoomReservationFX(RoomReservation roomReservation){
        RoomReservationFX roomReservationFX = new RoomReservationFX();
        roomReservationFX.setId(roomReservation.getId());
        roomReservationFX.setReservationFX(ReservationConverter.convertToReservationFX(roomReservation.getReservation()));
        roomReservationFX.setRoomFX(RoomConverter.convertToRoomFX(roomReservation.getRoom()));
        return roomReservationFX;
    }
}
