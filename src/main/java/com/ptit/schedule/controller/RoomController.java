package com.ptit.schedule.controller;

import com.ptit.schedule.dto.ApiResponse;
import com.ptit.schedule.dto.RoomRequest;
import com.ptit.schedule.dto.RoomResponse;
import com.ptit.schedule.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@Tag(name = "Room Management", description = "API quản lý phòng học")
public class RoomController {
    
    private final RoomService roomService;
    
    @Operation(summary = "Tạo phòng học mới", description = "Tạo phòng học mới trong hệ thống")
    @PostMapping
    public ResponseEntity<ApiResponse<RoomResponse>> createRoom(@RequestBody RoomRequest request) {
        try {
            RoomResponse response = roomService.createRoom(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success(response, "Tạo phòng học thành công!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.badRequest("Lỗi tạo phòng học: " + e.getMessage()));
        }
    }
    
    @Operation(summary = "Lấy danh sách phòng học", description = "Lấy danh sách tất cả phòng học")
    @GetMapping
    public ResponseEntity<ApiResponse<List<RoomResponse>>> getAllRooms() {
        try {
            List<RoomResponse> rooms = roomService.getAllRooms();
            return ResponseEntity.ok(ApiResponse.success(rooms, "Lấy danh sách phòng học thành công!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.badRequest("Lỗi lấy danh sách phòng học: " + e.getMessage()));
        }
    }
    
    @Operation(summary = "Lấy phòng học theo ID", description = "Lấy thông tin phòng học theo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoomResponse>> getRoomById(@PathVariable String id) {
        try {
            RoomResponse room = roomService.getRoomById(id);
            return ResponseEntity.ok(ApiResponse.success(room, "Lấy thông tin phòng học thành công!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.badRequest("Lỗi lấy thông tin phòng học: " + e.getMessage()));
        }
    }
    
    @Operation(summary = "Cập nhật phòng học", description = "Cập nhật thông tin phòng học")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoomResponse>> updateRoom(@PathVariable String id, @RequestBody RoomRequest request) {
        try {
            RoomResponse response = roomService.updateRoom(id, request);
            return ResponseEntity.ok(ApiResponse.success(response, "Cập nhật phòng học thành công!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.badRequest("Lỗi cập nhật phòng học: " + e.getMessage()));
        }
    }
    
    @Operation(summary = "Xóa phòng học", description = "Xóa phòng học khỏi hệ thống")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteRoom(@PathVariable String id) {
        try {
            roomService.deleteRoom(id);
            return ResponseEntity.ok(ApiResponse.success("Phòng học đã được xóa thành công!", "Xóa phòng học thành công!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.badRequest("Lỗi xóa phòng học: " + e.getMessage()));
        }
    }
    
    @Operation(summary = "Health check", description = "Kiểm tra trạng thái controller")
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Room Controller is OK");
    }
}
