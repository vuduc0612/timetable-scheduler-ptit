package com.ptit.schedule.controller;

import com.ptit.schedule.dto.ApiResponse;
import com.ptit.schedule.dto.MajorResponse;
import com.ptit.schedule.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api/majors")
@RequiredArgsConstructor
public class MajorController {
    private final MajorService majorService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<MajorResponse>>> getAllMajors() {
        List<MajorResponse> majors = majorService.getAllMajors();
        ApiResponse<List<MajorResponse>> response =  ApiResponse.success(majors, "Lấy danh sách ngành thành công");
        return ResponseEntity.ok(response);
    }
}
