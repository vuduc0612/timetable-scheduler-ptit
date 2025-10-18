package com.ptit.schedule.repository;

import com.ptit.schedule.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    
    // Tìm tất cả subject theo major
    List<Subject> findByMajorId(String majorId);
    
    // Tìm subject theo tên (tìm kiếm gần đúng)
    List<Subject> findBySubjectNameContainingIgnoreCase(String subjectName);
    
    // Tìm subject theo department
    List<Subject> findByDepartment(String department);
    
    // Tìm subject theo mã môn học
    List<Subject> findByIdContainingIgnoreCase(String subjectCode);
}
