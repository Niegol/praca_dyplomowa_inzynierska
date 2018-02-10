package Controllers.DataBase.Converters;

import Controllers.DataBase.FXModels.RoomFX;
import Controllers.DataBase.models.Room;

/**
 * Klasa umożliwiająca obustronne konwertowanie obiektów bazodanowych klasy Room na obiekty RoomFX będące ich reprezentacją
 * w oknie aplikacji
 */
public class RoomConverter {
    /**
     * Konwertuje obiekt widokowy na model bazodanowy
     * @param roomFX obiekt widoku klasy RoomFX
     * @return model bazodanowy klasy Room
     */
    public static Room convertToRoom(RoomFX roomFX){
        Room room = new Room();
        room.setIdRoom(roomFX.getId());
        room.setRoomNumber(Integer.parseInt(roomFX.getRoomNumber()));
        room.setFloor(roomFX.getFloor());
        room.setPavilon(roomFX.getPavilon());
        return room;
    }

    /**
     * Konwertuje model bazodanowy na obiekt widokowy
     * @param room model bazodanowy klasy
     * @return obiekt widoku klasy RoomFX
     */
    public static RoomFX convertToRoomFX(Room room){
        RoomFX roomFX = new RoomFX();
        roomFX.setId(room.getIdRoom());
        roomFX.setRoomNumber(String.valueOf(room.getRoomNumber()));
        roomFX.setFloor(room.getFloor());
        roomFX.setPavilon(room.getPavilon());
        return roomFX;
    }
}

