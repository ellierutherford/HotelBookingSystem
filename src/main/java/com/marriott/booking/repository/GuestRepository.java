package com.marriott.booking.repository;

import com.marriott.booking.exception.GuestNotFoundException;
import com.marriott.booking.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long>{

    @Query("select g from Guest g where g.id = ?1")
    Guest findByGuestId(Long booking_id) throws GuestNotFoundException;
}
