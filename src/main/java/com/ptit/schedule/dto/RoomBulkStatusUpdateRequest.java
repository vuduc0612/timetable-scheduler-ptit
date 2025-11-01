package com.ptit.schedule.dto;

import com.ptit.schedule.entity.RoomStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomBulkStatusUpdateRequest {

    @NotEmpty(message = "Danh sách mã phòng không được để trống")
    private List<String> roomCodes;

    @NotNull(message = "Trạng thái phòng không được để trống")
    private RoomStatus status;
}
