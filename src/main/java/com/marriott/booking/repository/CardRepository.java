package com.marriott.booking.repository;

import com.marriott.booking.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CreditCard, Long> {
}
