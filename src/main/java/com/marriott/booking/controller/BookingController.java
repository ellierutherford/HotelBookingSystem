package com.marriott.booking.controller;

import com.marriott.booking.Utils;
import com.marriott.booking.exception.CardNotFoundException;
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

    @RequestMapping({"/", "/home"})
    public String viewHomePage(){
        return "home";
    }

    @GetMapping("/findbooking")
    public String displayFindForm(){
        return "find";
    }
    @PostMapping("/find")
    public String retrieveBooking(@ModelAttribute("reservation") Booking b){
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

    @RequestMapping("/search")
    public String searchAvailability() {
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
            username = Utils.getLoggedOnUserName();
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

    @RequestMapping("/book")
    public String book(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate,
                         @RequestParam("numGuests") int numGuests, @RequestParam("roomId") Long room_id,
                         Model model, HttpSession session) throws CustomerNotFoundException {
        Optional<RoomAsset> roomAsset = roomAssetRepository.findById(room_id);
        Booking booking = new Booking();
        booking.setEndDate(endDate);
        booking.setStartDate(startDate);
        booking.setNumGuests(numGuests);
        roomAsset.ifPresent(room -> booking.setRoomAsset(room));
        String generatedBookingRef = UUID.randomUUID().toString().replaceAll("-", "");
        booking.setBookingRef(generatedBookingRef);

        session.setAttribute("booking", booking);
        model.addAttribute("booking", booking);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Boolean anonUser = authentication instanceof AnonymousAuthenticationToken;

        if(!anonUser) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return bookAsUser(booking, userDetails.getUsername());
        }

        return "bookingform";
    }

    public String bookAsUser(Booking b, String username) throws CustomerNotFoundException{
        User user = userRepository.findByUsername(username);
        Customer customer = customerRepository.findByCustomerId(user.getUser_id());
        b.setGuest_id(customer.getId());
        bookingRepository.save(b);
        return "redirect:/cards?userId="+customer.getId();
    }

    @PostMapping("/selectCard")
    public String selectCard(@RequestParam("selectedCardNumber") String selectedCardNumber, HttpSession session)
    throws CardNotFoundException{
        Booking booking = (Booking) session.getAttribute("booking");
        CreditCard card = cardRepository.findByCardNumber(selectedCardNumber);
        booking.setCard(card);
        // only set booking to active once a card is associated with it
        booking.setStatus(BookingStatus.ACTIVE);
        bookingRepository.save(booking);
        return "bookingSuccess";
    }

    @PostMapping("/bookingForm")
    public String saveCreatedBooking(@ModelAttribute("guest") Customer customer, HttpSession session, Model model){
        customerRepository.save(customer);
        Booking booking = (Booking) session.getAttribute("booking");
        booking.setGuest_id(customer.getId());
        bookingRepository.save(booking);
        model.addAttribute("booking", booking);
        return "cardformbooking";
    }

    @PostMapping("/completeBooking")
    public String saveCardDetails(@ModelAttribute("card") CreditCard card, @RequestParam Long bookingId)
        throws BookingNotFoundException{
        Booking b = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException(bookingId));
        b.setCard(card);
        // only set booking to active once a card is associated with it
        b.setStatus(BookingStatus.ACTIVE);
        card.setUserId(b.getGuest_id());
        cardRepository.save(card);
        return "bookingSuccess";
    }

    // Delete a Booking
    @RequestMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable(value = "id") Long bookingId, Model model) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        return "redirect:/";
    }
}