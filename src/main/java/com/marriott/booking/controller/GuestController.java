package com.marriott.booking.controller;

import com.marriott.booking.exception.GuestDeleteException;
import com.marriott.booking.exception.GuestNotFoundException;
import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.model.Guest;
import com.marriott.booking.model.Booking;
import com.marriott.booking.repository.GuestRepository;
import com.marriott.booking.repository.ReservationRepository;
import com.marriott.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GuestController {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    ReservationRepository ReservationRepository;
    @Autowired
    GuestRepository guestRepository;

    // Get a Single Guest
    @GetMapping("/guests/{id}")
    public String getGuestById(@PathVariable(value="id") Long guestId, Model model)
            throws GuestNotFoundException, BookingNotFoundException{
        Guest guest = guestRepository.findById(guestId)/*add the guest to the model*/
                .orElseThrow(() -> new GuestNotFoundException(guestId));
        model.addAttribute("guest", guest);

        List<Long> booking_ids = ReservationRepository.findBookingByGuestId(guest.getId()); /*find the bookings by the guest ID*/
        List<Booking> bookings = new ArrayList<>();
        for(Long booking_id: booking_ids){
            Booking booking =bookingRepository.findById(booking_id)
                    .orElseThrow(() -> new BookingNotFoundException(booking_id));
            bookings.add(booking);
        }
        model.addAttribute("bookings", bookings);
        return "editguestform";
    }


    // See All Guests on Homepage
    @RequestMapping({ "/guests"})
    public String viewGuestHomePage(Model model){
        List<Guest> listGuests = guestRepository.findAll();
        model.addAttribute("listGuests", listGuests);
        //need a bookingcount by guest so we can decide on the delete button.
        List<Integer> bookingCounts = new ArrayList<>();
        for (Guest guest : listGuests) {
            try {
                List<Long> bookingIds = ReservationRepository.findBookingByGuestId(guest.getId());
                int bookingCount = bookingIds.size();
                bookingCounts.add(bookingCount);
            } catch (BookingNotFoundException e) {
                e.printStackTrace();
                bookingCounts.add(0);
            }
        }


        model.addAttribute("bookingCounts", bookingCounts);

        return "welcomeGuest";
    }

    // Delete a Guest
    @RequestMapping("/deleteguest/{id}")
    public String deleteGuest(@PathVariable(value = "id") Long guestId, Model model) throws GuestNotFoundException{
        try {Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new GuestNotFoundException(guestId));
            guestRepository.delete(guest);
            return viewGuestHomePage(model); }
        catch (DataIntegrityViolationException e){
            throw new GuestDeleteException(e);
        }

    }

    // Create a Guest
    @RequestMapping("/newguest")
    public String createGuest(){
        return "guestform";
    }
    // Save Created Guest
    @PostMapping("/guests")
    public String saveCreatedGuest(@ModelAttribute("guest") Guest guest, Model model){
        guestRepository.save(guest);
        return viewGuestHomePage(model);
    }

    // Update a Guest
    // Get Guest By ID and open the editform

    // Save Updated Details
    @RequestMapping(value="/guests/save", method=RequestMethod.POST)
    public String updateGuest(@ModelAttribute("guest") Guest guest, Model model){
        guestRepository.save(guest);
        return viewGuestHomePage(model);
    }

}



