package com.ptit.schedule.service;

import com.ptit.schedule.dto.RoomRequest;
import com.ptit.schedule.dto.RoomResponse;
import com.ptit.schedule.dto.RoomStatusUpdateRequest;
import com.ptit.schedule.entity.RoomStatus;
import com.ptit.schedule.entity.RoomType;

import java.util.List;

public interface RoomService {

    // CRUD operations
    List<RoomResponse> getAllRooms();

    RoomResponse getRoomById(Long id);

    RoomResponse createRoom(RoomRequest roomRequest);

    RoomResponse updateRoom(Long id, RoomRequest roomRequest);

    void deleteRoom(Long id);

    // Status management
    RoomResponse updateRoomStatus(Long id, RoomStatusUpdateRequest statusRequest);

    // Query methods
    List<RoomResponse> getRoomsByBuilding(String day);

    List<RoomResponse> getRoomsByStatus(RoomStatus status);

    List<RoomResponse> getRoomsByType(RoomType type);

    List<RoomResponse> getAvailableRoomsWithCapacity(Integer requiredCapacity);

    List<RoomResponse> getRoomsByBuildingAndStatus(String day, RoomStatus status);

    List<RoomResponse> getRoomsByTypeAndStatus(RoomType type, RoomStatus status);
}
