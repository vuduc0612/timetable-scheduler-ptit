package com.ptit.schedule.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RoomRequest {
    
    @NotBlank(message = "Room number is required")
    @Size(max = 50, message = "Room number must not exceed 50 characters")
    private String roomNumber;
    
    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 1000, message = "Capacity must not exceed 1000")
    private Integer capacity;
    
    @NotBlank(message = "Building is required")
    @Size(max = 20, message = "Building must not exceed 20 characters")
    private String building;
    
    @NotBlank(message = "Room code is required")
    @Size(max = 50, message = "Room code must not exceed 50 characters")
    private String roomCode;
    
    @NotBlank(message = "Room type is required")
    @Size(max = 20, message = "Room type must not exceed 20 characters")
    private String roomType;
    
    @Size(max = 500, message = "Note must not exceed 500 characters")
    private String note;
}
