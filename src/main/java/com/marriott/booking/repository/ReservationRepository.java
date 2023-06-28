package com.marriott.booking.repository;

import com.marriott.booking.exception.GuestNotFoundException;
import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.model.Booking;
import com.marriott.booking.model.Reservation;
import com.marriott.booking.model.Guest;
import com.marriott.booking.model.RoomAsset;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;




@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query("select a.guest from Reservation a where a.booking.id = ?1")
    List<Guest> findGuestByBookingId(Long booking_id) throws GuestNotFoundException;

    @Query("select a.booking from Reservation a where a.guest.id = ?1")
    List<Guest> findBookingByGuestId(Long guest_id) throws GuestNotFoundException;

    @Query("SELECT a.booking FROM Reservation a WHERE a.booking.startDate <= :endDate AND a.booking.endDate >= :startDate")
    List<Booking> findBookingsByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);




    @Query("select a from Guest a where a not in (select aut.guest from Reservation aut where aut.booking.id = ?1)")
    List<Guest> findGuestsNotInBooking(Long booking_id);


    @Query("select a from Reservation a where a.booking.id = ?1 and a.guest.id = ?2")
    Reservation findReservationByGuestAndBookingId(Long booking_id, Long guest_id);


    /*@Modifying
    @Query("INSERT INTO Reservation (guest_id, booking_id) VALUES (?1, ?2)")
    @Transactional
    void writeReservation(Long guest_id, Long booking_id) throws GuestNotFoundException;*/


}



