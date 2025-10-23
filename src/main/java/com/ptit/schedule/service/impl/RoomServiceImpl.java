package com.ptit.schedule.service.impl;

import com.ptit.schedule.dto.RoomRequest;
import com.ptit.schedule.dto.RoomResponse;
import com.ptit.schedule.dto.RoomStatusUpdateRequest;
import com.ptit.schedule.entity.Room;
import com.ptit.schedule.entity.RoomStatus;
import com.ptit.schedule.entity.RoomType;
import com.ptit.schedule.repository.RoomRepository;
import com.ptit.schedule.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RoomResponse getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng với ID: " + id));
        return convertToResponse(room);
    }

    @Override
    public RoomResponse createRoom(RoomRequest roomRequest) {
        // Kiểm tra phòng đã tồn tại chưa
        Optional<Room> existingRoom = roomRepository.findByPhongAndDay(
                roomRequest.getPhong(), roomRequest.getDay());
        if (existingRoom.isPresent()) {
            throw new RuntimeException("Phòng " + roomRequest.getPhong() +
                    " trong tòa nhà " + roomRequest.getDay() + " đã tồn tại");
        }

        Room room = Room.builder()
                .phong(roomRequest.getPhong())
                .capacity(roomRequest.getCapacity())
                .day(roomRequest.getDay())
                .type(roomRequest.getType())
                .status(RoomStatus.AVAILABLE) // Mặc định là trống
                .note(roomRequest.getNote())
                .build();

        Room savedRoom = roomRepository.save(room);
        return convertToResponse(savedRoom);
    }

    @Override
    public RoomResponse updateRoom(Long id, RoomRequest roomRequest) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng với ID: " + id));

        // Kiểm tra phòng khác có cùng số phòng và tòa nhà không
        Optional<Room> existingRoom = roomRepository.findByPhongAndDay(
                roomRequest.getPhong(), roomRequest.getDay());
        if (existingRoom.isPresent() && !existingRoom.get().getId().equals(id)) {
            throw new RuntimeException("Phòng " + roomRequest.getPhong() +
                    " trong tòa nhà " + roomRequest.getDay() + " đã tồn tại");
        }

        room.setPhong(roomRequest.getPhong());
        room.setCapacity(roomRequest.getCapacity());
        room.setDay(roomRequest.getDay());
        room.setType(roomRequest.getType());
        room.setNote(roomRequest.getNote());

        Room updatedRoom = roomRepository.save(room);
        return convertToResponse(updatedRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy phòng với ID: " + id);
        }
        roomRepository.deleteById(id);
    }

    @Override
    public RoomResponse updateRoomStatus(Long id, RoomStatusUpdateRequest statusRequest) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng với ID: " + id));

        room.setStatus(statusRequest.getStatus());
        Room updatedRoom = roomRepository.save(room);
        return convertToResponse(updatedRoom);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> getRoomsByBuilding(String day) {
        return roomRepository.findByDay(day).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> getRoomsByStatus(RoomStatus status) {
        return roomRepository.findByStatus(status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> getRoomsByType(RoomType type) {
        return roomRepository.findByType(type).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> getAvailableRoomsWithCapacity(Integer requiredCapacity) {
        return roomRepository.findAvailableRoomsWithCapacity(requiredCapacity).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> getRoomsByBuildingAndStatus(String day, RoomStatus status) {
        return roomRepository.findByDayAndStatus(day, status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> getRoomsByTypeAndStatus(RoomType type, RoomStatus status) {
        return roomRepository.findByTypeAndStatus(type, status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private RoomResponse convertToResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .phong(room.getPhong())
                .capacity(room.getCapacity())
                .day(room.getDay())
                .type(room.getType())
                .typeDisplayName(room.getType().getDisplayName())
                .status(room.getStatus())
                .statusDisplayName(room.getStatus().getDisplayName())
                .note(room.getNote())
                .build();
    }
}
