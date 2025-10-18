package com.ptit.schedule.service;

import com.ptit.schedule.dto.SubjectRequest;
import com.ptit.schedule.dto.SubjectResponse;

import java.util.List;

public interface SubjectService {
    
    /**
     * Lấy tất cả subjects
     */
    List<SubjectResponse> getAllSubjects();
    
    /**
     * Lấy subject theo ID
     */
    SubjectResponse getSubjectById(String id);
    
    /**
     * Lấy subjects theo major ID
     */
    List<SubjectResponse> getSubjectsByMajorId(String majorId);

    /**
     * Tạo subject mới
     */
    SubjectResponse createSubject(SubjectRequest request);
    
    /**
     * Cập nhật subject
     */
    SubjectResponse updateSubject(String id, SubjectRequest request);
    
    /**
     * Xóa subject
     */
    void deleteSubject(String id);
}
