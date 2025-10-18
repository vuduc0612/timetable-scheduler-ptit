package com.ptit.schedule.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "subjects")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    private String id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "students_per_class")
    private int studentsPerClass;    // Sĩ số mỗi lớp học phần

    @Column(name = "number_of_classes")
    private int numberOfClasses;     // Số lớp học phần

    private int credits;            // Số tín chỉ

    @Column(name = "theory_hours")  // Số tiết lý thuyết
    private int theoryHours;

    @Column(name = "exercise_hours")  // Số tiết bài tập
    private int exerciseHours;

    @Column(name = "project_hours")  // Số tiết btl
    private int projectHours;

    @Column(name = "lab_hours") // Số tiết thực hành
    private int labHours;

    @Column(name = "self_study_hours")  // Số tiết tự học
    private int selfStudyHours;

    private String department; // Bộ môn

    @Column(name = "exam_format") // Hình thức thi
    private String examFormat;

    @ManyToOne
    @JoinColumn(name = "major_id", nullable = false)
    private Major major;

}