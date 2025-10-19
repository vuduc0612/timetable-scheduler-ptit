package com.ptit.schedule.service.impl;

import com.ptit.schedule.dto.RoomRequest;
import com.ptit.schedule.dto.RoomResponse;
import com.ptit.schedule.entity.Room;
import com.ptit.schedule.repository.RoomRepository;
import com.ptit.schedule.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RoomServiceImpl implements RoomService {
    
    private final RoomRepository roomRepository;
    
    @Override
    public RoomResponse createRoom(RoomRequest request) {
        log.info("Creating room: {}", request.getRoomNumber());
        
        // Kiểm tra room number đã tồn tại chưa
        if (roomRepository.existsByRoomNumber(request.getRoomNumber())) {
            throw new RuntimeException("Room number already exists: " + request.getRoomNumber());
        }
        
        // Kiểm tra room code đã tồn tại chưa
        if (roomRepository.existsByRoomCode(request.getRoomCode())) {
            throw new RuntimeException("Room code already exists: " + request.getRoomCode());
        }
        
        Room room = Room.builder()
                .id(UUID.randomUUID().toString())
                .roomNumber(request.getRoomNumber())
                .capacity(request.getCapacity())
                .building(request.getBuilding())
                .roomCode(request.getRoomCode())
                .roomType(request.getRoomType())
                .note(request.getNote())
                .build();
        
        Room savedRoom = roomRepository.save(room);
        log.info("Created room: {} with ID: {}", savedRoom.getRoomNumber(), savedRoom.getId());
        
        return RoomResponse.fromEntity(savedRoom);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> getAllRooms() {
        log.info("Getting all rooms");
        return roomRepository.findAll().stream()
                .map(RoomResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public RoomResponse getRoomById(String id) {
        log.info("Getting room by ID: {}", id);
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + id));
        return RoomResponse.fromEntity(room);
    }
    
    @Override
    public RoomResponse updateRoom(String id, RoomRequest request) {
        log.info("Updating room: {}", id);
        
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + id));
        
        // Kiểm tra room number đã tồn tại chưa (trừ room hiện tại)
        if (!room.getRoomNumber().equals(request.getRoomNumber()) && 
            roomRepository.existsByRoomNumber(request.getRoomNumber())) {
            throw new RuntimeException("Room number already exists: " + request.getRoomNumber());
        }
        
        // Kiểm tra room code đã tồn tại chưa (trừ room hiện tại)
        if (!room.getRoomCode().equals(request.getRoomCode()) && 
            roomRepository.existsByRoomCode(request.getRoomCode())) {
            throw new RuntimeException("Room code already exists: " + request.getRoomCode());
        }
        
        room.setRoomNumber(request.getRoomNumber());
        room.setCapacity(request.getCapacity());
        room.setBuilding(request.getBuilding());
        room.setRoomCode(request.getRoomCode());
        room.setRoomType(request.getRoomType());
        room.setNote(request.getNote());
        
        Room updatedRoom = roomRepository.save(room);
        log.info("Updated room: {} with ID: {}", updatedRoom.getRoomNumber(), updatedRoom.getId());
        
        return RoomResponse.fromEntity(updatedRoom);
    }
    
    @Override
    public void deleteRoom(String id) {
        log.info("Deleting room: {}", id);
        
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + id));
        
        roomRepository.delete(room);
        log.info("Deleted room: {} with ID: {}", room.getRoomNumber(), room.getId());
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByRoomNumber(String roomNumber) {
        return roomRepository.existsByRoomNumber(roomNumber);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByRoomCode(String roomCode) {
        return roomRepository.existsByRoomCode(roomCode);
    }
}
