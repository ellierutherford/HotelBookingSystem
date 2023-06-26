package com.marriott.booking.repository;

import com.marriott.booking.model.AssetBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetBookingRepository extends JpaRepository<AssetBooking, Long>{
}
