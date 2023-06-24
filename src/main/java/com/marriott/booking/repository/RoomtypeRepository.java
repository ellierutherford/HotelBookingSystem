package com.marriott.booking.repository;

import com.marriott.booking.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomtypeRepository extends JpaRepository<RoomType, Long>{
}
