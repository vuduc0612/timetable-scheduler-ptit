package com.ptit.schedule.dto;

import com.ptit.schedule.entity.RoomStatus;
import com.ptit.schedule.entity.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {

    private Long id;
    private String phong;
    private Integer capacity;
    private String day;
    private RoomType type;
    private String typeDisplayName;
    private RoomStatus status;
    private String statusDisplayName;
    private String note;
}
