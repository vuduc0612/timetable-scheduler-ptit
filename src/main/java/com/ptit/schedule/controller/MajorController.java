package com.ptit.schedule.controller;

import com.ptit.schedule.dto.MajorRequest;
import com.ptit.schedule.dto.MajorResponse;
import com.ptit.schedule.service.MajorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/majors")
@RequiredArgsConstructor
@Tag(name = "Major Management", description = "API quản lý ngành")
public class MajorController {
    
    private final MajorService majorService;
    
    @Operation(summary = "Health check", description = "Kiểm tra trạng thái Major Controller")
    @ApiResponse(responseCode = "200", description = "Major Controller hoạt động bình thường")
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("Major Controller is OK", HttpStatus.OK);
    }
    
    @Operation(summary = "Lấy tất cả ngành", description = "Trả về danh sách tất cả ngành")
    @ApiResponse(responseCode = "200", description = "Thành công")
    @GetMapping
    public ResponseEntity<List<MajorResponse>> getAllMajors() {
        List<MajorResponse> majors = majorService.getAllMajors();
        return ResponseEntity.ok(majors);
    }
    
    @Operation(summary = "Lấy ngành theo ID", description = "Trả về thông tin ngành theo ID")
    @ApiResponse(responseCode = "200", description = "Thành công")
    @ApiResponse(responseCode = "404", description = "Không tìm thấy ngành")
    @GetMapping("/{id}")
    public ResponseEntity<MajorResponse> getMajorById(@PathVariable String id) {
        MajorResponse major = majorService.getMajorById(id);
        return ResponseEntity.ok(major);
    }
    
    @Operation(summary = "Lấy ngành theo khoa", description = "Trả về danh sách ngành theo faculty ID")
    @ApiResponse(responseCode = "200", description = "Thành công")
    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<MajorResponse>> getMajorsByFacultyId(@PathVariable String facultyId) {
        List<MajorResponse> majors = majorService.getMajorsByFacultyId(facultyId);
        return ResponseEntity.ok(majors);
    }
    
    @Operation(summary = "Tạo ngành mới", description = "Tạo ngành mới")
    @ApiResponse(responseCode = "201", description = "Tạo thành công")
    @ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ")
    @PostMapping
    public ResponseEntity<MajorResponse> createMajor(@Valid @RequestBody MajorRequest request) {
        MajorResponse major = majorService.createMajor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(major);
    }
    
    @Operation(summary = "Cập nhật ngành", description = "Cập nhật thông tin ngành")
    @ApiResponse(responseCode = "200", description = "Cập nhật thành công")
    @ApiResponse(responseCode = "404", description = "Không tìm thấy ngành")
    @ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ")
    @PutMapping("/{id}")
    public ResponseEntity<MajorResponse> updateMajor(@PathVariable String id, 
                                                   @Valid @RequestBody MajorRequest request) {
        MajorResponse major = majorService.updateMajor(id, request);
        return ResponseEntity.ok(major);
    }
    
    @Operation(summary = "Xóa ngành", description = "Xóa ngành theo ID")
    @ApiResponse(responseCode = "204", description = "Xóa thành công")
    @ApiResponse(responseCode = "404", description = "Không tìm thấy ngành")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMajor(@PathVariable String id) {
        majorService.deleteMajor(id);
        return ResponseEntity.noContent().build();
    }
}
