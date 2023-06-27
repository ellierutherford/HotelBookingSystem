package com.marriott.booking.repository;

import com.marriott.booking.exception.RoomNotFoundException;
import com.marriott.booking.model.AssetBooking;
import com.marriott.booking.model.RoomAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AssetBookingRepository extends JpaRepository<AssetBooking, Long>{

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM AssetBooking a WHERE a.roomAsset.id = :roomAssetId AND a.assetbooking_date = :assetBookingDate")
    boolean isAvailable(@Param("roomAssetId") Long roomAssetId, @Param("assetBookingDate") LocalDate assetBookingDate) throws RoomNotFoundException;




}
