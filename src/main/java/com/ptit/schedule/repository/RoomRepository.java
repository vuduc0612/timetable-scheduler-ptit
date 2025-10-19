package com.ptit.schedule.repository;

import com.ptit.schedule.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    
    Optional<Room> findByRoomNumber(String roomNumber);
    
    Optional<Room> findByRoomCode(String roomCode);
    
    boolean existsByRoomNumber(String roomNumber);
    
    boolean existsByRoomCode(String roomCode);
}
