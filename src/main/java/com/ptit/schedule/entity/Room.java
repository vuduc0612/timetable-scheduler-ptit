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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-increment primary key

    @Column(name = "phong", nullable = false)
    @NotBlank(message = "Số phòng không được để trống")
    @Size(max = 10, message = "Số phòng không được vượt quá 10 ký tự")
    private String phong; // Số phòng

    @Column(name = "capacity", nullable = false)
    @NotNull(message = "Sức chứa không được để trống")
    @Min(value = 1, message = "Sức chứa phải lớn hơn 0")
    @Max(value = 500, message = "Sức chứa không được vượt quá 500")
    private Integer capacity; // Sức chứa

    @Column(name = "building", nullable = false)
    @NotBlank(message = "Tòa nhà không được để trống")
    @Size(max = 10, message = "Tòa nhà không được vượt quá 10 ký tự")
    private String day; // Tòa nhà (A1, A2, A3, NT)

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    @NotNull(message = "Loại phòng không được để trống")
    private RoomType type; // Loại phòng

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @NotNull(message = "Trạng thái phòng không được để trống")
    @Builder.Default
    private RoomStatus status = RoomStatus.AVAILABLE; // Trạng thái phòng, mặc định là trống

    @Column(name = "note", length = 1000)
    @Size(max = 1000, message = "Ghi chú không được vượt quá 1000 ký tự")
    private String note; // Ghi chú
}
