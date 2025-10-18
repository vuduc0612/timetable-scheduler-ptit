package com.ptit.schedule.repository;

import com.ptit.schedule.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MajorRepository extends JpaRepository<Major, String> {
    
    // Tìm majors theo faculty ID
    List<Major> findByFacultyId(String facultyId);
    
    // Tìm majors theo tên (tìm kiếm gần đúng)
    List<Major> findByMajorNameContainingIgnoreCase(String majorName);
    
    // Tìm majors theo khóa học
    List<Major> findByClassYear(String classYear);
    
    // Tìm major theo ID và khóa học
    Optional<Major> findByIdAndClassYear(String id, String classYear);
}
