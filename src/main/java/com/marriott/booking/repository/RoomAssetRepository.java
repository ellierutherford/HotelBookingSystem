package com.marriott.booking.repository;

import com.marriott.booking.exception.GuestNotFoundException;
import com.marriott.booking.exception.RoomNotFoundException;
import com.marriott.booking.model.Guest;
import com.marriott.booking.model.RoomAsset;
import com.marriott.booking.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomAssetRepository extends JpaRepository<RoomAsset, Long> {
    @Query("select a from RoomAsset a where a.roomType.id = ?1")
    List<RoomAsset> findByRoomTypeId(Long roomType_id) throws RoomNotFoundException;
}
