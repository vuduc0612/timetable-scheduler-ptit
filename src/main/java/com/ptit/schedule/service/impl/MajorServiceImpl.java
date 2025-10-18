package com.ptit.schedule.service.impl;

import com.ptit.schedule.dto.MajorRequest;
import com.ptit.schedule.dto.MajorResponse;
import com.ptit.schedule.entity.Faculty;
import com.ptit.schedule.entity.Major;
import com.ptit.schedule.repository.FacultyRepository;
import com.ptit.schedule.repository.MajorRepository;
import com.ptit.schedule.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MajorServiceImpl implements MajorService {
    
    private final MajorRepository majorRepository;
    private final FacultyRepository facultyRepository;
    
    /**
     * Lấy tất cả majors
     */
    @Override
    @Transactional(readOnly = true)
    public List<MajorResponse> getAllMajors() {
        return majorRepository.findAll()
                .stream()
                .map(MajorResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    /**
     * Lấy major theo ID
     */
    @Override
    @Transactional(readOnly = true)
    public MajorResponse getMajorById(String id) {
        Major major = majorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Major not found with id: " + id));
        return MajorResponse.fromEntity(major);
    }
    
    /**
     * Lấy majors theo faculty ID
     */
    @Override
    @Transactional(readOnly = true)
    public List<MajorResponse> getMajorsByFacultyId(String facultyId) {
        return majorRepository.findByFacultyId(facultyId)
                .stream()
                .map(MajorResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    /**
     * Tạo major mới
     */
    @Override
    public MajorResponse createMajor(MajorRequest request) {
        // Kiểm tra faculty có tồn tại không
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + request.getFacultyId()));
        
        Major major = new Major();
        major.setId(UUID.randomUUID().toString());
        major.setMajorName(request.getMajorName());
        major.setNumberOfStudents(request.getNumberOfStudents());
        major.setClassYear(request.getClassYear());
        major.setFaculty(faculty);
        
        Major savedMajor = majorRepository.save(major);
        return MajorResponse.fromEntity(savedMajor);
    }
    
    /**
     * Cập nhật major
     */
    @Override
    public MajorResponse updateMajor(String id, MajorRequest request) {
        Major major = majorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Major not found with id: " + id));
        
        // Kiểm tra faculty có tồn tại không
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + request.getFacultyId()));
        
        major.setMajorName(request.getMajorName());
        major.setNumberOfStudents(request.getNumberOfStudents());
        major.setClassYear(request.getClassYear());
        major.setFaculty(faculty);
        
        Major savedMajor = majorRepository.save(major);
        return MajorResponse.fromEntity(savedMajor);
    }
    
    /**
     * Xóa major
     */
    @Override
    public void deleteMajor(String id) {
        if (!majorRepository.existsById(id)) {
            throw new RuntimeException("Major not found with id: " + id);
        }
        majorRepository.deleteById(id);
    }
}
