package com.ptit.schedule.dto;

import com.ptit.schedule.entity.Subject;
import lombok.Data;

@Data
public class SubjectResponse {
    
    private String id;
    private String subjectName;
    private Integer studentsPerClass;
    private Integer numberOfClasses;
    private Integer credits;
    private Integer theoryHours;
    private Integer exerciseHours;
    private Integer projectHours;
    private Integer labHours;
    private Integer selfStudyHours;
    private String department;
    private String examFormat;
    private String majorId;
    private String majorName;
    
    public static SubjectResponse fromEntity(Subject subject) {
        SubjectResponse response = new SubjectResponse();
        response.setId(subject.getId());
        response.setSubjectName(subject.getSubjectName());
        response.setStudentsPerClass(subject.getStudentsPerClass());
        response.setNumberOfClasses(subject.getNumberOfClasses());
        response.setCredits(subject.getCredits());
        response.setTheoryHours(subject.getTheoryHours());
        response.setExerciseHours(subject.getExerciseHours());
        response.setProjectHours(subject.getProjectHours());
        response.setLabHours(subject.getLabHours());
        response.setSelfStudyHours(subject.getSelfStudyHours());
        response.setDepartment(subject.getDepartment());
        response.setExamFormat(subject.getExamFormat());
        response.setMajorId(subject.getMajor().getId());
        response.setMajorName(subject.getMajor().getMajorName());
        return response;
    }
}
