package com.ptit.schedule.service;

import com.ptit.schedule.dto.RoomRequest;
import com.ptit.schedule.dto.RoomResponse;

import java.util.List;

public interface RoomService {
    
    RoomResponse createRoom(RoomRequest request);
    
    List<RoomResponse> getAllRooms();
    
    RoomResponse getRoomById(String id);
    
    RoomResponse updateRoom(String id, RoomRequest request);
    
    void deleteRoom(String id);
    
    boolean existsByRoomNumber(String roomNumber);
    
    boolean existsByRoomCode(String roomCode);
}
