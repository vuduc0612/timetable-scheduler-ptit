package com.ptit.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConflictResult {
    
    private List<RoomConflict> roomConflicts;
    private List<TeacherConflict> teacherConflicts;
    private int totalConflicts;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoomConflict {
        private String room;
        private ScheduleEntry.TimeSlot timeSlot;  // Đối tượng TimeSlot đầy đủ
        private List<ScheduleEntry> conflictingSchedules;
        
        public String getConflictDescription() {
            return String.format("Phòng %s bị trùng vào %s", room, timeSlot.getDisplayInfo());
        }
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeacherConflict {
        private String teacherId;
        private String teacherName;
        private ScheduleEntry.TimeSlot timeSlot;  // Đối tượng TimeSlot đầy đủ
        private List<ScheduleEntry> conflictingSchedules;
        
        public String getConflictDescription() {
            return String.format("Giảng viên %s (%s) bị trùng lịch vào %s", 
                    teacherName, teacherId, timeSlot.getDisplayInfo());
        }
    }
    
    public int getTotalConflicts() {
        return (roomConflicts != null ? roomConflicts.size() : 0) + 
               (teacherConflicts != null ? teacherConflicts.size() : 0);
    }
}