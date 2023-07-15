package com.marriott.booking.repository;

import com.marriott.booking.exception.CustomerNotFoundException;
import com.marriott.booking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    @Query("select c from Customer c where c.id = ?1")
    Customer findByCustomerId(Long booking_id) throws CustomerNotFoundException;
}
