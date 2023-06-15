package com.marriott.booking.controller;

import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.model.Booking;
import com.marriott.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    // welcome page
    @RequestMapping({"/"})
    public String viewHomePage(Model model){
        return "welcome";
    }

    // Get a booking by its ID
    @GetMapping("/bookings/{id}")
    public String getReservationById(@PathVariable(value="id") Long reservationId, Model model)
            throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(reservationId)
                .orElseThrow(() -> new BookingNotFoundException(reservationId));
        model.addAttribute("booking", booking);
        return "booking";
    }
}



