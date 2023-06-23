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
    GuestRepository guestRepository;

    @RequestMapping({ "/guests"})
    public String viewGuestHomePage(Model model){
        List<Guest> listGuests = guestRepository.findAll();
        model.addAttribute("listGuests", listGuests);

        System.out.println("All the guests" );
        /* List<Integer> bookCounts = new ArrayList<>();
        for (Guest guest : listGuests) {
            try {
                List<Booking> books = guestRepository.findBookingByGuest(guest);
                int bookCount = books.size();
                bookCounts.add(bookCount);
            } catch (BookingNotFoundException e) {
                e.printStackTrace();
                bookCounts.add(0);
            }
        }


        model.addAttribute("bookCounts", bookCounts);*/

        return "welcomeGuest";
    }

    @GetMapping("/guests/{id}")
    public String getGuestById(@PathVariable(value="id") Long guestId, Model model)
            throws GuestNotFoundException, BookingNotFoundException{
        Guest guest = guestRepository.findById(guestId)/*add the guest to the model*/
                .orElseThrow(() -> new GuestNotFoundException(guestId));
        model.addAttribute("guest", guest);
        System.out.println("Get Guests: " + guest.getId() + "With first name" + guest.getGuest_first_name() );

        /*List<Booking> books = guestRepository.findBookingByGuest(guest.getId()); find the books by the guest ID
        List<Booking> books = new ArrayList<>();
        for(Booking books: book){
            Booking book =guestRepository.findById(book)
                    .orElseThrow(() -> new BookingNotFoundException(book));
            books.add(book);
        }
        model.addAttribute("books", book);*/

        return "editguestform";
    }

    // Create a Guest
    @RequestMapping("/newguest")
    public String createGuest(){
        return "guestform";
    }
    // Delete a Guest
    @RequestMapping("/deleteguest/{id}")
    public String deleteGuest(@PathVariable(value = "id") Long guestId, Model model) throws GuestNotFoundException{
        try {Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new GuestNotFoundException(guestId));
            System.out.println("Guest Deleted: " + guest.getId() + "With first name" + guest.getGuest_first_name() );
            guestRepository.delete(guest);
            return viewGuestHomePage(model); }
        catch (DataIntegrityViolationException e){
            throw new GuestDeleteException(e);
        }

    }


    // Save Created Guest
    @PostMapping("/guests")
    public String saveCreatedGuest(@ModelAttribute("guest") Guest guest, Model model){
        guestRepository.save(guest);
        System.out.println("Save Created Guest: " + guest.getId() + "With first name " + guest.getGuest_first_name() );
        return viewGuestHomePage(model);
    }

    // Update a Guest
    // Get Guest By ID and open the editform

    // Save Updated Details
    @RequestMapping(value="/guests/save", method=RequestMethod.POST)
    public String updateGuest(@ModelAttribute("guest") Guest guest, Model model){
        guestRepository.save(guest);
        System.out.println("Updated : " + guest.getId() + "With first name " + guest.getGuest_first_name() );
        return viewGuestHomePage(model);
    }

}



