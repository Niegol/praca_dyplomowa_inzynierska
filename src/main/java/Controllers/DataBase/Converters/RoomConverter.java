package Controllers.DataBase.Converters;

import Controllers.DataBase.FXModels.RoomFX;
import Controllers.DataBase.models.Room;

public class RoomConverter {
    public static Room convertToRoom(RoomFX roomFX){
        Room room = new Room();
        room.setIdRoom(roomFX.getId());
        room.setRoomNumber(Integer.parseInt(roomFX.getRoomNumber()));
        room.setFloor(roomFX.getFloor());
        room.setPavilon(roomFX.getPavilon());
        return room;
    }

    public static RoomFX convertToRoomFX(Room room){
        RoomFX roomFX = new RoomFX();
        roomFX.setId(room.getIdRoom());
        roomFX.setRoomNumber(String.valueOf(room.getRoomNumber()));
        roomFX.setFloor(room.getFloor());
        roomFX.setPavilon(room.getPavilon());
        return roomFX;
    }
}
