package com.ptit.schedule.repository;

import com.ptit.schedule.entity.Room;
import com.ptit.schedule.entity.RoomStatus;
import com.ptit.schedule.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    // Tìm phòng theo tòa nhà
    @Query("SELECT r FROM Room r WHERE r.day = :day")
    List<Room> findByDay(@Param("day") String day);

    // Tìm phòng theo loại
    List<Room> findByType(RoomType type);

    // Tìm phòng theo trạng thái
    List<Room> findByStatus(RoomStatus status);

    // Tìm phòng theo tòa nhà và trạng thái
    @Query("SELECT r FROM Room r WHERE r.day = :day AND r.status = :status")
    List<Room> findByDayAndStatus(@Param("day") String day, @Param("status") RoomStatus status);

    // Tìm phòng theo loại và trạng thái
    List<Room> findByTypeAndStatus(RoomType type, RoomStatus status);

    // Tìm phòng theo sức chứa tối thiểu
    List<Room> findByCapacityGreaterThanEqual(Integer minCapacity);

    // Tìm phòng trống có sức chứa đủ cho số sinh viên
    @Query("SELECT r FROM Room r WHERE r.status = 'AVAILABLE' AND r.capacity >= :requiredCapacity ORDER BY r.capacity ASC")
    List<Room> findAvailableRoomsWithCapacity(@Param("requiredCapacity") Integer requiredCapacity);

    // Tìm phòng theo tòa nhà và sức chứa
    @Query("SELECT r FROM Room r WHERE r.day = :day AND r.capacity >= :minCapacity")
    List<Room> findByDayAndCapacityGreaterThanEqual(@Param("day") String day,
            @Param("minCapacity") Integer minCapacity);

    // Tìm phòng theo số phòng và tòa nhà
    @Query("SELECT r FROM Room r WHERE r.phong = :phong AND r.day = :day")
    Optional<Room> findByPhongAndDay(@Param("phong") String phong, @Param("day") String day);
}
