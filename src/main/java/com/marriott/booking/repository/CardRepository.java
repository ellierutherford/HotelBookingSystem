package com.marriott.booking.repository;

import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.exception.CardNotFoundException;
import com.marriott.booking.model.Booking;
import com.marriott.booking.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CreditCard, Long> {
    @Query("select c from CreditCard c where c.userId = ?1")
    List<CreditCard> findByUserId(Long userId) throws CardNotFoundException;

    @Query("select c from CreditCard c where c.cardNumber = ?1")
    CreditCard findByCardNumber(String cardNumber) throws CardNotFoundException;
}
