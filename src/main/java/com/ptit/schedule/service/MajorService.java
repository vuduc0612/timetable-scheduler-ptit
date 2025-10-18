package com.ptit.schedule.service;

import com.ptit.schedule.dto.MajorRequest;
import com.ptit.schedule.dto.MajorResponse;

import java.util.List;

public interface MajorService {
    
    /**
     * Lấy tất cả majors
     */
    List<MajorResponse> getAllMajors();
    
    /**
     * Lấy major theo ID
     */
    MajorResponse getMajorById(String id);
    
    /**
     * Lấy majors theo faculty ID
     */
    List<MajorResponse> getMajorsByFacultyId(String facultyId);
    
    /**
     * Tạo major mới
     */
    MajorResponse createMajor(MajorRequest request);
    
    /**
     * Cập nhật major
     */
    MajorResponse updateMajor(String id, MajorRequest request);
    
    /**
     * Xóa major
     */
    void deleteMajor(String id);
}
