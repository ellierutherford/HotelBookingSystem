package com.marriott.booking.controller;


import com.marriott.booking.exception.GuestNotFoundException;
import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.model.Guest;
import com.marriott.booking.model.Booking;
import com.marriott.booking.model.Reservation;
import com.marriott.booking.model.RoomType;
import com.marriott.booking.repository.GuestRepository;
import com.marriott.booking.repository.ReservationRepository;
import com.marriott.booking.repository.BookingRepository;
import com.marriott.booking.repository.RoomtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    RoomtypeRepository roomtypeRepository;


    // Get a Single Booking
    @GetMapping("/bookings/{id}")
    public String getBookingById(@PathVariable(value="id") Long bookingId, Model model)
            throws BookingNotFoundException, GuestNotFoundException{
        Booking booking = bookingRepository.findById(bookingId)/*add the booking to the model*/
                .orElseThrow(() -> new BookingNotFoundException(bookingId));
        model.addAttribute("booking", booking);

        List<Guest> guests = reservationRepository.findGuestByBookingId(booking.getId());
        System.out.println("2 get a single booking by IDing a Single Booking for: " +booking.getleadguest_first_name() +" and guest it's guest" + reservationRepository.findGuestByBookingId(booking.getId()));

        model.addAttribute("listguests", guests);
        model.addAttribute("missingGuests", reservationRepository.findGuestsNotInBooking(booking.getId()));
        System.out.println("2Cond1 We can send the guests that are not in this booking to edit screen and they are: " + reservationRepository.findGuestsNotInBooking(booking.getId()) +" !!!");

        return "editform";
    }

    @RequestMapping({"/", "/list"})
    public String viewHomePage(Model model){
        List<Booking> listBookings = bookingRepository.findAll();
        model.addAttribute("listBookings", listBookings);

        List<Guest> listGuests = guestRepository.findAll();
        int guestCount = listGuests.size(); // Get the number of guests in the list
        model.addAttribute("guestCount", guestCount); // Add the guest count to the model*/
        System.out.println("1 Welcome page List these bookings" + listBookings + " and this many registered guests "+guestCount );
        return "welcome";
    }

    @RequestMapping("/new")
    public String createBooking(Model model) {
        List<Guest> listGuests = guestRepository.findAll();
        model.addAttribute("listGuests", listGuests);
        System.out.println("2 createBooking Form displayed with checkbox array of guests" +listGuests );
        return "bookingform";
    }
    @PostMapping("/bookings")
    public String saveCreatedBooking(@ModelAttribute("booking") Booking booking, @RequestParam("guestIds") Long[] guestIds, Model model) throws GuestNotFoundException {
        System.out.println("3 redirect on saving of booking: " + booking.getleadguest_first_name() + " and guests " + Arrays.toString(guestIds) + " !!!");
        bookingRepository.save(booking);
        for (Long guestId : guestIds) {
            Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new GuestNotFoundException(guestId));
            Reservation reservation = new Reservation(booking, guest);
            reservationRepository.save(reservation);
            //reservationRepository.save(new Reservation(booking, guest));
            System.out.println("4 Added guest " + guestId + " to booking " + booking.getId());
        }

        model.addAttribute("bookings", booking);
        System.out.println("5 I'm post save of booking " + booking.getleadguest_first_name() + " and guests " + Arrays.toString(guestIds) + ".");
        return "redirect:/list";
    }

    // Delete a Booking
    @RequestMapping("/delete/{id}")
    public String deleteBooking(@PathVariable(value = "id") Long bookingId, Model model) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));

        System.out.println("Booking Deleted: " + booking.getId() + "With " + booking.getleadguest_first_name() );
        bookingRepository.delete(booking);
        return "redirect:/list";

    }

    @RequestMapping(value = "bookings/save", method = RequestMethod.POST)
    public String updateNote( @ModelAttribute("booking")  Booking booking, @RequestParam("missingGuests") Long[] guestIds, Model model) throws BookingNotFoundException, GuestNotFoundException {
        System.out.println("1e booking edit in the saving of booking: " +booking.getleadguest_first_name() + " with existing guest" + reservationRepository.findGuestByBookingId(booking.getId()) +" !!!");
                //apply guest to reservation
        System.out.println("1e booking edit in the saving of booking: " +booking.getleadguest_first_name() + " with new guests" + guestIds+" !!!");
        for (Long guestId : guestIds) {
            Guest guest = new Guest();
            guest.setGuest_first_name(booking.getleadguest_first_name());
            guest.setGuest_last_name(booking.getleadguest_last_name());
            reservationRepository.save(new Reservation(bookingRepository.save(booking), guestRepository.save(guest)));
            System.out.println("1eL Added guest " + guestId + " to booking " + booking.getId());
        }
        bookingRepository.save(booking);
        return "redirect:/list";
    }

    @RequestMapping(value = "bookings/add/{id}", method = RequestMethod.POST)
    public String addGuest(@PathVariable(value = "id") Long bookingId, @RequestParam("guestMissing") Long guestId,  Model model)  throws BookingNotFoundException, GuestNotFoundException{

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException(bookingId));
        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new GuestNotFoundException(guestId));
        System.out.println("saving of booking: " +booking.getleadguest_first_name() + "and guest" + guest.getGuest_first_name() + " ." );
        System.out.println("saving of booking: " +booking.getleadguest_first_name() + "and guest" + guest.getGuest_last_name() + " ." );
        reservationRepository.save(new Reservation(booking, guest));
        return "redirect:/bookings/"+String.valueOf(bookingId);

    }


    @RequestMapping("/experiment")
    public String createStrangerBooking(Model model) {
        System.out.println("1a createStrangerBooking Form displayed" );
        //need to send out roomtypes, full list
        List<RoomType> listroomTypes = roomtypeRepository.findAll();
        model.addAttribute("listroomTypes", listroomTypes);

        return "newguestbooking";
    }



    @PostMapping("/newguestbookings")
    public String saveCreatedStrangerBooking(@ModelAttribute("booking") Booking booking, Model model) throws GuestNotFoundException {
        System.out.println("2a redirect on saving of a brand new booking!" + booking.getleadguest_first_name() + "we make the anon lead booker the first guest.");
        Guest guest = new Guest();
        guest.setGuest_first_name(booking.getleadguest_first_name());
        guest.setGuest_last_name(booking.getleadguest_last_name());
        reservationRepository.save(new Reservation(bookingRepository.save(booking), guestRepository.save(guest)));
        System.out.println("3a Save Created New Guest: " + guest.getId() + "With first name " + guest.getGuest_first_name() + "With last name ." + guest.getGuest_last_name() );
        return "redirect:/";
    }

    @RequestMapping(value = "newguestbookings/save", method = RequestMethod.POST)
    public String updateStrangerBooking( @ModelAttribute("booking")  Booking booking, Model model) throws BookingNotFoundException, GuestNotFoundException {
        System.out.println("NEVER RUN I redirect on in the saving of a new customer booking: " +booking.getleadguest_first_name() + " and guest" + reservationRepository.findGuestByBookingId(booking.getId()) +" !!!");
        bookingRepository.save(booking);
        return "redirect:/list";
    }

    @RequestMapping(value = "newguestbookings/add/{id}", method = RequestMethod.POST)
    public String addGuestFromSite(@PathVariable(value = "id") Long bookingId, @RequestParam("guestMissing") Long guestId,  Model model)  throws BookingNotFoundException, GuestNotFoundException{

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException(bookingId));
        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new GuestNotFoundException(guestId));
        System.out.println("saving of booking: " +booking.getleadguest_first_name() + "and guest" + guest.getGuest_first_name() + " ." );
        System.out.println("saving of booking: " +booking.getleadguest_first_name() + "and guest" + guest.getGuest_last_name() + " ." );
        reservationRepository.save(new Reservation(booking, guest));

        return "redirect:/newguestbookings/"+String.valueOf(bookingId);

    }

    // this chains in the deletion. but has never run or been tested, it's from the example code
    @RequestMapping("bookings/removeGuest/{id}")
    public String deleteReservation(@PathVariable(value = "id") Long bookingId, @RequestParam("id") Long guestId,  Model model) throws  BookingNotFoundException, GuestNotFoundException{
        Reservation aut = reservationRepository.findReservationByGuestAndBookingId(bookingId, guestId);
        reservationRepository.delete(aut);
        System.out.println("NEVER RUN We're deleting a booking so removeReservation guest ID:" +guestId + " and reservations " +aut );
        return "redirect:/bookings/" + String.valueOf(bookingId) ;

    }

}