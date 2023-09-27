package com.marriott.booking.repository;

import com.marriott.booking.model.CheckEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckEventService extends JpaRepository<CheckEvent, Long> {
    // Define custom query methods here if needed.
}
