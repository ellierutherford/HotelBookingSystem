package com.marriott.booking.controller;

import com.marriott.booking.exception.CustomerNotFoundException;
import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.model.*;
import com.marriott.booking.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

@Controller
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Autowired
    RoomAssetRepository roomAssetRepository;

    @PostMapping("/find")
    public String retrieveBooking(@ModelAttribute("booking") Booking b){
        return "redirect:/bookings/" + b.getBookingRef();
    }

    @GetMapping("/bookings/{ref}")
    public String getBookingByRef(@PathVariable(value="ref") String bookingRef, Model model)
            throws BookingNotFoundException {
        Booking booking = bookingRepository.findByBookingRef(bookingRef);
        model.addAttribute("booking", booking);

        // Figure out if the booking is far enough out to allow guest to cancel
        LocalDate now = LocalDate.now();
        long daysUntilStay = Duration.between(now.atStartOfDay(), booking.getStartDate().atStartOfDay()).toDays();
        boolean allowCancel = false;

        if (daysUntilStay >= 1) {
            allowCancel = true;
        }
        model.addAttribute("allowCancel", allowCancel);

        return "booking";
    }

    @RequestMapping({"/", "/home"})
    public String viewHomePage(){
        return "home";
    }

    @RequestMapping("/book")
    public String book(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate,
                         @RequestParam("numGuests") int numGuests, @RequestParam("roomId") Long room_id,
                         Model model, HttpSession session) throws CustomerNotFoundException {
        Optional<RoomAsset> roomAsset = roomAssetRepository.findById(room_id);
        Booking booking = new Booking();
        booking.setEndDate(endDate);
        booking.setStartDate(startDate);
        booking.setNumGuests(numGuests);
        roomAsset.ifPresent(room -> booking.setRoomAsset(room)); //throw instead?
        booking.setStatus(BookingStatus.ACTIVE);
        String generatedBookingRef = UUID.randomUUID().toString().replaceAll("-", "");
        booking.setBookingRef(generatedBookingRef);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Boolean anonUser = authentication instanceof AnonymousAuthenticationToken;
        String displayPage = "bookingform";
        if(!anonUser) {
            displayPage = "cardform";
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername());
            Customer customer = customerRepository.findByCustomerId(user.getUser_id());
            booking.setGuest_id(customer.getId());
            bookingRepository.save(booking);
        }
        session.setAttribute("booking", booking);
        model.addAttribute("booking", booking);
        return displayPage;
    }

    @RequestMapping("/search")
    public String searchAvailability() {
        System.out.println("in search...");
        return "searchform";
    }

    @PostMapping("/availability")
    public String searchAvailability(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate,
                                     @RequestParam("numGuests") int numGuests, Model model) throws Exception{

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("numGuests", numGuests);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if(authentication instanceof AnonymousAuthenticationToken){
            username = "anonymous";
        }
        else{
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }
        model.addAttribute("username", username);

        List<RoomAsset> availableRoomAssets = roomAssetRepository.findAvailableRoomsByCapacity(startDate, endDate, numGuests);

        if(availableRoomAssets.size()==0){
            throw new Exception("No room available!"); //throw better exception
        }
        else {
            model.addAttribute("availableRooms", availableRoomAssets);
        }

        return "availablerooms";
    }

    @PostMapping("/bookingForm")
    public String saveCreatedBooking(@ModelAttribute("guest") Customer customer, HttpSession session, Model model){
        customerRepository.save(customer);
        Booking booking = (Booking) session.getAttribute("booking");
        booking.setGuest_id(customer.getId());
        bookingRepository.save(booking);
        model.addAttribute("booking", booking);
        return "cardform";
    }

    @PostMapping("/completeBooking")
    public String saveCardDetails(@ModelAttribute("card") CreditCard card, @RequestParam Long bookingId)
        throws BookingNotFoundException{
        Booking b = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException(bookingId));
        b.setCard(card);
        card.setUserId(b.getGuest_id()); //this is a bit gross
        cardRepository.save(card);
        return "bookingSuccess";
    }

    // Delete a Booking
    @RequestMapping("/delete/{id}")
    public String deleteBooking(@PathVariable(value = "id") Long bookingId, Model model) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));
        bookingRepository.delete(booking);
        return "redirect:/";
    }
}