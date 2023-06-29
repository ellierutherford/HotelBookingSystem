package com.marriott.booking.repository;

import com.marriott.booking.exception.GuestNotFoundException;
import com.marriott.booking.exception.RoomNotFoundException;
import com.marriott.booking.model.Guest;
import com.marriott.booking.model.RoomAsset;
import com.marriott.booking.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RoomAssetRepository extends JpaRepository<RoomAsset, Long> {
     @Query("select a from RoomAsset a where a.roomType.id = ?1")
    List<RoomAsset> findByRoomTypeId(Long roomType_id) throws RoomNotFoundException;

    @Query("SELECT e FROM RoomAsset e WHERE e.id NOT IN (SELECT g.id FROM Booking g WHERE (:start_date BETWEEN g.startDate and g.endDate or :end_date BETWEEN g.startDate and g.endDate))")
    List<RoomAsset> findAvailableRooms(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date);

    @Query("SELECT e FROM RoomAsset e WHERE e.roomType.id = :roomType_id AND e.id NOT IN (SELECT g.id FROM Booking g WHERE (:start_date BETWEEN g.startDate AND g.endDate OR :end_date BETWEEN g.startDate AND g.endDate))")
    List<RoomAsset> findAvailableRoomsByType(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date, @Param("roomType_id") Long roomType_id);
}
