package com.ptit.schedule.service;

import com.ptit.schedule.dto.SubjectFullDTO;
import com.ptit.schedule.dto.SubjectMajorDTO;
import com.ptit.schedule.dto.SubjectRequest;
import com.ptit.schedule.dto.SubjectResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface SubjectService {

    // Lấy tất cả subjects(thông tin đặc trưng) cùng majors
    List<SubjectMajorDTO> getAllSubjects();

    // Lấy tất cả subjects với phân trang
    Page<SubjectFullDTO> getAllSubjectsWithPagination(int page, int size, String sortBy, String sortDir);

    // Lấy subjects theo major ID
    List<SubjectResponse> getSubjectsByMajorId(Integer majorId);

    // Lấy subject kèm mã ngành theo năm học và loại chương trình
    List<SubjectMajorDTO> getSubjectAndMajorCodeByClassYear(String classYear, String programType, List<String> majorCodes);

    // Nhóm các ngành có cùng tập môn học chung
    List<Set<String>> groupMajorsBySharedSubjects(String classYear, String programType);

    // Lấy danh sách môn học chung
    List<SubjectMajorDTO> getCommonSubjects();

    // Tạo subject mới
    SubjectResponse createSubject(SubjectRequest request);
    
    // Cập nhật subject
    SubjectResponse updateSubject(Long id, SubjectRequest request);
    
    // Xóa subject
    void deleteSubject(Long id);
}
