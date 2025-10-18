package com.ptit.schedule.controller;

import com.ptit.schedule.dto.ApiResponse;
import com.ptit.schedule.dto.SubjectRequest;
import com.ptit.schedule.dto.SubjectResponse;
import com.ptit.schedule.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subjects")
@RequiredArgsConstructor
@Tag(name = "Subject Management", description = "API quản lý môn học")
public class SubjectController {
    
    private final SubjectService subjectService;

    @Operation(summary = "Health check", description = "Kiểm tra trạng thái server")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Server hoạt động bình thường")
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        ApiResponse<String> response = ApiResponse.success("Server is OK");
        return ResponseEntity.ok(response);
    }
    
    @Operation(summary = "Lấy tất cả môn học", description = "Trả về danh sách tất cả môn học")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Thành công")
    @GetMapping
    public ResponseEntity<ApiResponse<List<SubjectResponse>>> getAllSubjects() {
        List<SubjectResponse> subjects = subjectService.getAllSubjects();
        ApiResponse<List<SubjectResponse>> response = ApiResponse.success(subjects, "Lấy danh sách môn học thành công");
        return ResponseEntity.ok(response);
    }
    
    @Operation(summary = "Lấy môn học theo ID", description = "Trả về thông tin môn học theo ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Thành công")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Không tìm thấy môn học")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SubjectResponse>> getSubjectById(@PathVariable String id) {
        try {
            SubjectResponse subject = subjectService.getSubjectById(id);
            ApiResponse<SubjectResponse> response = ApiResponse.success(subject, "Lấy thông tin môn học thành công");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<SubjectResponse> response = ApiResponse.notFound("Không tìm thấy môn học với ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    @Operation(summary = "Lấy môn học theo ngành", description = "Trả về danh sách môn học theo major ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Thành công")
    @GetMapping("/major/{majorId}")
    public ResponseEntity<ApiResponse<List<SubjectResponse>>> getSubjectsByMajorId(@PathVariable String majorId) {
        List<SubjectResponse> subjects = subjectService.getSubjectsByMajorId(majorId);
        ApiResponse<List<SubjectResponse>> response = ApiResponse.success(subjects, "Lấy danh sách môn học theo ngành thành công");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Tạo môn học mới", description = "Tạo môn học mới với logic tự động tạo major nếu cần")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Tạo thành công")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ")
    @PostMapping
    public ResponseEntity<ApiResponse<SubjectResponse>> createSubject(@Valid @RequestBody SubjectRequest request) {
        try {
            System.out.println("Received Subject Request: " + request);
            SubjectResponse subject = subjectService.createSubject(request);
            ApiResponse<SubjectResponse> response = ApiResponse.created(subject, "Tạo môn học thành công");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            ApiResponse<SubjectResponse> response = ApiResponse.badRequest(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @Operation(summary = "Cập nhật môn học", description = "Cập nhật thông tin môn học")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cập nhật thành công")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Không tìm thấy môn học")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SubjectResponse>> updateSubject(@PathVariable String id, 
                                                        @Valid @RequestBody SubjectRequest request) {
        try {
            SubjectResponse subject = subjectService.updateSubject(id, request);
            ApiResponse<SubjectResponse> response = ApiResponse.success(subject, "Cập nhật môn học thành công");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                ApiResponse<SubjectResponse> response = ApiResponse.notFound(e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                ApiResponse<SubjectResponse> response = ApiResponse.badRequest(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
    }
    
    @Operation(summary = "Xóa môn học", description = "Xóa môn học theo ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Xóa thành công")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Không tìm thấy môn học")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSubject(@PathVariable String id) {
        try {
            subjectService.deleteSubject(id);
            ApiResponse<Void> response = ApiResponse.success(null, "Xóa môn học thành công");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<Void> response = ApiResponse.notFound(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
