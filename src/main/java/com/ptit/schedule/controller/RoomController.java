package com.ptit.schedule.controller;

import com.ptit.schedule.dto.ApiResponse;
import com.ptit.schedule.dto.RoomRequest;
import com.ptit.schedule.dto.RoomResponse;
import com.ptit.schedule.dto.RoomStatusUpdateRequest;
import com.ptit.schedule.entity.RoomStatus;
import com.ptit.schedule.entity.RoomType;
import com.ptit.schedule.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RoomController {

        private final RoomService roomService;

        @GetMapping
        public ResponseEntity<ApiResponse<List<RoomResponse>>> getAllRooms() {
                List<RoomResponse> rooms = roomService.getAllRooms();
                return ResponseEntity.ok(ApiResponse.<List<RoomResponse>>builder()
                                .success(true)
                                .message("Lấy danh sách phòng thành công")
                                .data(rooms)
                                .build());
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<RoomResponse>> getRoomById(@PathVariable Long id) {
                RoomResponse room = roomService.getRoomById(id);
                return ResponseEntity.ok(ApiResponse.<RoomResponse>builder()
                                .success(true)
                                .message("Lấy thông tin phòng thành công")
                                .data(room)
                                .build());
        }

        @PostMapping
        public ResponseEntity<ApiResponse<RoomResponse>> createRoom(@Valid @RequestBody RoomRequest roomRequest) {
                RoomResponse room = roomService.createRoom(roomRequest);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.<RoomResponse>builder()
                                                .success(true)
                                                .message("Tạo phòng mới thành công")
                                                .data(room)
                                                .build());
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<RoomResponse>> updateRoom(
                        @PathVariable Long id,
                        @Valid @RequestBody RoomRequest roomRequest) {
                RoomResponse room = roomService.updateRoom(id, roomRequest);
                return ResponseEntity.ok(ApiResponse.<RoomResponse>builder()
                                .success(true)
                                .message("Cập nhật thông tin phòng thành công")
                                .data(room)
                                .build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> deleteRoom(@PathVariable Long id) {
                roomService.deleteRoom(id);
                return ResponseEntity.ok(ApiResponse.<Void>builder()
                                .success(true)
                                .message("Xóa phòng thành công")
                                .build());
        }

        @PatchMapping("/{id}/status")
        public ResponseEntity<ApiResponse<RoomResponse>> updateRoomStatus(
                        @PathVariable Long id,
                        @Valid @RequestBody RoomStatusUpdateRequest statusRequest) {
                RoomResponse room = roomService.updateRoomStatus(id, statusRequest);
                return ResponseEntity.ok(ApiResponse.<RoomResponse>builder()
                                .success(true)
                                .message("Cập nhật trạng thái phòng thành công")
                                .data(room)
                                .build());
        }

        @GetMapping("/building/{building}")
        public ResponseEntity<ApiResponse<List<RoomResponse>>> getRoomsByBuilding(@PathVariable String building) {
                List<RoomResponse> rooms = roomService.getRoomsByBuilding(building);
                return ResponseEntity.ok(ApiResponse.<List<RoomResponse>>builder()
                                .success(true)
                                .message("Lấy danh sách phòng theo tòa nhà thành công")
                                .data(rooms)
                                .build());
        }

        @GetMapping("/status/{status}")
        public ResponseEntity<ApiResponse<List<RoomResponse>>> getRoomsByStatus(@PathVariable RoomStatus status) {
                List<RoomResponse> rooms = roomService.getRoomsByStatus(status);
                return ResponseEntity.ok(ApiResponse.<List<RoomResponse>>builder()
                                .success(true)
                                .message("Lấy danh sách phòng theo trạng thái thành công")
                                .data(rooms)
                                .build());
        }

        @GetMapping("/type/{type}")
        public ResponseEntity<ApiResponse<List<RoomResponse>>> getRoomsByType(@PathVariable RoomType type) {
                List<RoomResponse> rooms = roomService.getRoomsByType(type);
                return ResponseEntity.ok(ApiResponse.<List<RoomResponse>>builder()
                                .success(true)
                                .message("Lấy danh sách phòng theo loại thành công")
                                .data(rooms)
                                .build());
        }

        @GetMapping("/available")
        public ResponseEntity<ApiResponse<List<RoomResponse>>> getAvailableRoomsWithCapacity(
                        @RequestParam Integer capacity) {
                List<RoomResponse> rooms = roomService.getAvailableRoomsWithCapacity(capacity);
                return ResponseEntity.ok(ApiResponse.<List<RoomResponse>>builder()
                                .success(true)
                                .message("Lấy danh sách phòng trống có đủ sức chứa thành công")
                                .data(rooms)
                                .build());
        }

        @GetMapping("/building/{building}/status/{status}")
        public ResponseEntity<ApiResponse<List<RoomResponse>>> getRoomsByBuildingAndStatus(
                        @PathVariable String building,
                        @PathVariable RoomStatus status) {
                List<RoomResponse> rooms = roomService.getRoomsByBuildingAndStatus(building, status);
                return ResponseEntity.ok(ApiResponse.<List<RoomResponse>>builder()
                                .success(true)
                                .message("Lấy danh sách phòng theo tòa nhà và trạng thái thành công")
                                .data(rooms)
                                .build());
        }

        @GetMapping("/type/{type}/status/{status}")
        public ResponseEntity<ApiResponse<List<RoomResponse>>> getRoomsByTypeAndStatus(
                        @PathVariable RoomType type,
                        @PathVariable RoomStatus status) {
                List<RoomResponse> rooms = roomService.getRoomsByTypeAndStatus(type, status);
                return ResponseEntity.ok(ApiResponse.<List<RoomResponse>>builder()
                                .success(true)
                                .message("Lấy danh sách phòng theo loại và trạng thái thành công")
                                .data(rooms)
                                .build());
        }
}
