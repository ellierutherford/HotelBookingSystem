package com.marriott.booking.repository;
import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
    @Query("select b from Booking b where b.bookingRef = ?1")
    Booking findByBookingRef(String bookingRef) throws BookingNotFoundException;
}
