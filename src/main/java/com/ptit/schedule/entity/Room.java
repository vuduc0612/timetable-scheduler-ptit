package com.ptit.schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "rooms")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    private String id;

    @Column(name = "room_number", nullable = false)
    @NotBlank(message = "Room number is required")
    @Size(max = 50, message = "Room number must not exceed 50 characters")
    private String roomNumber;  // phong

    @Column(name = "capacity", nullable = false)
    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 1000, message = "Capacity must not exceed 1000")
    private Integer capacity;

    @Column(name = "building", nullable = false)
    @NotBlank(message = "Building is required")
    @Size(max = 20, message = "Building must not exceed 20 characters")
    private String building;  // day

    @Column(name = "room_code", nullable = false)
    @NotBlank(message = "Room code is required")
    @Size(max = 50, message = "Room code must not exceed 50 characters")
    private String roomCode;  // ma_phong

    @Column(name = "room_type", nullable = false)
    @NotBlank(message = "Room type is required")
    @Size(max = 20, message = "Room type must not exceed 20 characters")
    private String roomType;  // type

    @Column(name = "note")
    @Size(max = 500, message = "Note must not exceed 500 characters")
    private String note;

    // TODO: Uncomment when Schedule entity is created
    // @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    // private List<Schedule> schedules;
}
