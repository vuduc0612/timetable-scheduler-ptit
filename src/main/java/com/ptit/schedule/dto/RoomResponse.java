package com.ptit.schedule.dto;

import com.ptit.schedule.entity.Room;
import lombok.Data;

@Data
public class RoomResponse {
    
    private String id;
    private String roomNumber;
    private Integer capacity;
    private String building;
    private String roomCode;
    private String roomType;
    private String note;
    
    public static RoomResponse fromEntity(Room room) {
        RoomResponse response = new RoomResponse();
        response.setId(room.getId());
        response.setRoomNumber(room.getRoomNumber());
        response.setCapacity(room.getCapacity());
        response.setBuilding(room.getBuilding());
        response.setRoomCode(room.getRoomCode());
        response.setRoomType(room.getRoomType());
        response.setNote(room.getNote());
        return response;
    }
}
