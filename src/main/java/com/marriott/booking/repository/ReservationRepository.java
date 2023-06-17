package com.marriott.booking.repository;


import com.marriott.booking.exception.GuestNotFoundException;
import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.model.Reservation;
import com.marriott.booking.model.ReservationId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {


    @Query("select a.id_guest from Reservation a where a.id_booking = ?1")
    List<Long> findGuestByBookingId(Long booking_id) throws GuestNotFoundException;
    /*Takes a bookingID, gets objects (guests) that match that booking.*/


    @Query("select a.id_booking from Reservation a where a.id_guest = ?1")
    List<Long> findBookingByGuestId(Long guest_id) throws BookingNotFoundException;
    /*Takes a guest_ID, gets objects (bookings) that match that guest.*/

    @Modifying
    @Query("INSERT INTO Reservation (id_guest, id_booking) VALUES (?1, ?2)")
    @Transactional
    void writeReservation(Long guest_id, Long booking_id) throws GuestNotFoundException;


}



