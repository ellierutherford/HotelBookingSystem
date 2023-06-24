package com.marriott.booking.repository;

import com.marriott.booking.model.RoomAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomAssetRepository extends JpaRepository<RoomAsset, Long>{
}
