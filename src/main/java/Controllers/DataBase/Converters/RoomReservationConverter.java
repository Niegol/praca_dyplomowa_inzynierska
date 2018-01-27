package Controllers.DataBase.Converters;

import Controllers.DataBase.FXModels.RoomReservationFX;
import Controllers.DataBase.models.RoomReservation;

public class RoomReservationConverter {
    public static RoomReservation convertToRoomReservation(RoomReservationFX roomReservationFX){
        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setId(roomReservationFX.getId());
        roomReservation.setReservation(ReservationConverter.convertToReservation(roomReservationFX.getReservationFX()));
        roomReservation.setRoom(RoomConverter.convertToRoom(roomReservationFX.getRoomFX()));
        return  roomReservation;
    }

    public static RoomReservationFX convertToRoomReservationFX(RoomReservation roomReservation){
        RoomReservationFX roomReservationFX = new RoomReservationFX();
        roomReservationFX.setId(roomReservation.getId());
        roomReservationFX.setReservationFX(ReservationConverter.convertToReservationFX(roomReservation.getReservation()));
        roomReservationFX.setRoomFX(RoomConverter.convertToRoomFX(roomReservation.getRoom()));
        return roomReservationFX;
    }
}
