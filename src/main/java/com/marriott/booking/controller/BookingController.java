package com.marriott.booking.controller;


import com.marriott.booking.exception.GuestNotFoundException;
import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.model.Guest;
import com.marriott.booking.model.ReservationId;
import com.marriott.booking.model.Booking;
import com.marriott.booking.repository.GuestRepository;
import com.marriott.booking.repository.ReservationRepository;
import com.marriott.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    ReservationRepository ReservationRepository;
    @Autowired
    GuestRepository guestRepository;

    // Get a Single Booking
    @GetMapping("/bookings/{id}")
    public String getBookingById(@PathVariable(value="id") Long bookingId, Model model)
            throws BookingNotFoundException, GuestNotFoundException{
        Booking booking = bookingRepository.findById(bookingId)/*add the booking to the model*/
                .orElseThrow(() -> new BookingNotFoundException(bookingId));
        model.addAttribute("booking", booking);

        List<Long> guest_ids = ReservationRepository.findGuestByBookingId(booking.getId()); /*find the guests by the booking ID*/
        List<Guest> guests = new ArrayList<>();
        for(Long guest_id: guest_ids){
            Guest guest =guestRepository.findById(guest_id)
                    .orElseThrow(() -> new GuestNotFoundException(guest_id));
            guests.add(guest);
        }
        model.addAttribute("guests", guests);

        List<Guest> listGuests = guestRepository.findAll();
        model.addAttribute("listGuests", listGuests);

        return "editform";
    }


    // See All Bookings on Homepage
    @RequestMapping({"/", "/list"})
    public String viewHomePage(Model model){
        List<Booking> listBookings = bookingRepository.findAll();
        model.addAttribute("listBookings", listBookings);

        List<Guest> listGuests = guestRepository.findAll();
        int guestCount = listGuests.size(); // Get the number of guests in the list
        model.addAttribute("guestCount", guestCount); // Add the guest count to the model
        return "welcome";
    }

    // Delete a Booking
    @RequestMapping("/delete/{id}")
    public String deleteBooking(@PathVariable(value = "id") Long bookingId, Model model) throws BookingNotFoundException{
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));
        bookingRepository.delete(booking);
        return viewHomePage(model);
    }

    // Create a Booking
    @RequestMapping("/new")
    public String createBooking(Model model) {
        List<Guest> listGuests = guestRepository.findAll();
        model.addAttribute("listGuests", listGuests);
        return "bookingform";
    }
    @PostMapping("/bookings")
    public String saveCreatedBooking(@ModelAttribute("booking") Booking booking, @RequestParam("guests") List<Long> guestIds, Model model) throws GuestNotFoundException {
        // Save the booking to get an ID
        bookingRepository.save(booking);
        System.out.println("DEBUG saved booking is: " + booking.getId());
        for (Long guestId : guestIds) {
            System.out.println("Creating ReservationID with booking id: " + booking.getId() + " and guest id: " + guestId);
            // Create an instance of ReservationId for this guest
            ReservationId ReservationId = new ReservationId(booking.getId(), guestId);
            System.out.println("ReservationID has guest id..: " + ReservationId.getId_Guest() + " and Booking id: " + ReservationId.getId_Booking());
            ReservationRepository.writeReservation(ReservationId.getId_Guest(),ReservationId.getId_Booking());
        }
        return viewHomePage(model);
    }

    // Update a Booking
    // Get Booking By ID and open the editform
    // Save Updated Details
    @RequestMapping(value="/bookings/save", method=RequestMethod.POST)
    public String updateBooking(@ModelAttribute("booking") Booking booking, Model model){
        bookingRepository.save(booking);
        return viewHomePage(model);
    }

}



