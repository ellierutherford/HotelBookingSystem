package com.marriott.booking.repository;

import com.marriott.booking.exception.RoomNotFoundException;
import com.marriott.booking.model.RoomAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomAssetRepository extends JpaRepository<RoomAsset, Long> {
     @Query("select a from RoomAsset a where a.roomType.id = ?1")
    List<RoomAsset> findByRoomTypeId(Long roomType_id) throws RoomNotFoundException;

    @Query("SELECT r FROM RoomAsset r WHERE r.roomType.id = :roomType_id AND r.max_guests >= :num_guests AND r.id NOT IN (SELECT g.roomasset FROM Booking g WHERE (:start_date >= g.startDate AND :start_date < g.endDate OR :end_date >= g.startDate and :end_date < g.endDate))")
    List<RoomAsset> findAvailableRoomsByTypeAndCapacity(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date,
                                                        @Param("roomType_id") Long room_type ,@Param("num_guests") int num_guests);

    @Query("SELECT r FROM RoomAsset r WHERE r.max_guests >= :num_guests AND r.id NOT IN (SELECT g.roomasset FROM Booking g WHERE (:start_date >= g.startDate AND :start_date < g.endDate OR :end_date >= g.startDate and :end_date < g.endDate))")
    List<RoomAsset> findAvailableRoomsByCapacity(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date, @Param("num_guests") int num_guests);

    @Query("SELECT r FROM RoomAsset r WHERE r.roomType.id = :roomType_id AND r.id NOT IN (SELECT g.roomasset FROM Booking g WHERE (:start_date >= g.startDate AND :start_date < g.endDate OR :end_date >= g.startDate and :end_date < g.endDate))")
    List<RoomAsset> findAvailableRoomsByType(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date, @Param("roomType_id") Long roomType_id);
}
