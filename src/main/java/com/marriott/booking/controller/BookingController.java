package com.marriott.booking.controller;

import com.marriott.booking.exception.GuestNotFoundException;
import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.exception.RoomAssetNotFoundException;
import com.marriott.booking.exception.RoomNotFoundException;
import com.marriott.booking.model.*;
import com.marriott.booking.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Autowired
    RoomAssetRepository roomAssetRepository;

    @GetMapping("/bookings/{id}")
    public String getBookingById(@PathVariable(value="id") Long bookingId, Model model)
            throws BookingNotFoundException, GuestNotFoundException{
        Booking booking = bookingRepository.findById(bookingId)/*add the booking to the model*/
                .orElseThrow(() -> new BookingNotFoundException(bookingId));
        model.addAttribute("booking", booking);

        List<Guest> guests = reservationRepository.findGuestByBookingId(booking.getId());
        model.addAttribute("listguests", guests);
        model.addAttribute("missingGuests", reservationRepository.findGuestsNotInBooking(booking.getId()));
        return "editform";
    }

    @RequestMapping({"/", "/list"})
    public String viewHomePage(Model model){
        List<Booking> listBookings = bookingRepository.findAll();
        model.addAttribute("listBookings", listBookings);

        List<Guest> listGuests = guestRepository.findAll();
        int guestCount = listGuests.size(); // Get the number of guests in the list
        model.addAttribute("guestCount", guestCount); // Add the guest count to the model*/
        return "welcome";
    }

    @RequestMapping("/new")
    public String createBooking(Model model) {
        List<Guest> listGuests = guestRepository.findAll();
        model.addAttribute("listGuests", listGuests);
        List<RoomType> listroomTypes = roomTypeRepository.findAll();
        model.addAttribute("listroomTypes", listroomTypes);
        return "bookingform";
    }

    @RequestMapping("/auth")
    public String continueAsGuestOrLogin(){
        return "continueOrLogin";
    }

    @RequestMapping("/bookel")
    public String bookEl(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate,
                         @RequestParam("numGuests") int numGuests, @RequestParam("roomId") Long room_id,
                         Model model){
        model.addAttribute("startDate", startDate );
        model.addAttribute("endDate", endDate);
        model.addAttribute("numGuests", numGuests);
        model.addAttribute("roomId", room_id);
        return "bookingform";
    }

    @RequestMapping("/search")
    public String searchAvailability(Model model) {
        System.out.println("in search...");
        return "searchform";
    }

    @PostMapping("/availability")
    public String searchAvailability(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate,
                                     @RequestParam("numGuests") int numGuests, Model model) throws Exception{

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("numGuests", numGuests);
        List<RoomAsset> availableRoomAssets = roomAssetRepository.findAvailableRoomsByCapacity(startDate, endDate, numGuests);

        if(availableRoomAssets.size()==0){
            throw new Exception("No room available!"); //throw better exception
        }
        else {
            model.addAttribute("availableRooms", availableRoomAssets);
        }

        return "AvailableRooms";
    }

    @PostMapping("/bookingsel")
    public String saveCreatedBookingEl(@ModelAttribute("guest") Guest guest, Model m){
        //Booking b = new Booking(m)
        //Guest g = new Guest();
        guestRepository.save(guest);
        //booking.setGuest_id(guest.getId());
        //bookingRepository.save(booking);
        return "redirect:/list";
    }

    // Delete a Booking
    /*@RequestMapping("/delete/{id}")
    public String deleteBooking(@PathVariable(value = "id") Long bookingId, Model model) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));

        System.out.println("1 ID Action Booking Deleted: " + booking.getId() + "With " + booking.getleadguest_first_name() );
        bookingRepository.delete(booking);
        return "redirect:/list";

    }*/

    /*@RequestMapping(value = "bookings/save", method = RequestMethod.POST)
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
    }*/

    /*@RequestMapping(value = "bookings/add/{id}", method = RequestMethod.POST)
    public String addGuest(@PathVariable(value = "id") Long bookingId, @RequestParam("guestMissing") Long guestId,  Model model)  throws BookingNotFoundException, GuestNotFoundException{

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException(bookingId));
        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new GuestNotFoundException(guestId));
        System.out.println("saving of booking: " +booking.getleadguest_first_name() + "and guest" + guest.getGuest_first_name() + " ." );
        System.out.println("saving of booking: " +booking.getleadguest_first_name() + "and guest" + guest.getGuest_last_name() + " ." );
        reservationRepository.save(new Reservation(booking, guest));
        return "redirect:/bookings/"+String.valueOf(bookingId);

    }*/


    @RequestMapping("/experiment")
    public String createStrangerBooking(Model model) {
        System.out.println("1a createStrangerBooking Form displayed" );
        //need to send out roomtypes, full list for drop down

        return "bookingsanon";
    }
    /*@PostMapping("/bookingsanon")
    public String saveCreatedStrangerBooking(@ModelAttribute("booking") Booking booking,  Model model, HttpSession session) throws BookingNotFoundException, GuestNotFoundException {

        System.out.println("New unknown booker with Name: " + booking.getleadguest_first_name() + " " + booking.getleadguest_last_name() +" .");
        System.out.println("Booking Startdate: " + booking.getStartDate() + ". ");
        System.out.println("Booking Enddate: " + booking.getEndDate() + ". ");

        List<RoomType> listroomTypes = roomTypeRepository.findAll();
        model.addAttribute("listroomTypes", listroomTypes);

        Guest guest = new Guest();
        guest.setGuest_first_name(booking.getleadguest_first_name());
        guest.setGuest_last_name(booking.getleadguest_last_name());
        guestRepository.save(guest);

        Reservation reservation = new Reservation(booking, guest);
        session.setAttribute("reservation", reservation);
        session.setAttribute("leadguest", guest);

        System.out.println("3a Save Created New Guest: " + guest.getId() + "With first name " + guest.getGuest_first_name() + "With last name ." + guest.getGuest_last_name() );
        model.addAttribute("guest", guest);
        return "bookingsanonstep2";
    }

    @PostMapping("/bookingsanonstep2")
    public String saveCreatedStrangerBookingStep2(@ModelAttribute("booking") Booking booking, Model model, @RequestParam("listroomType") Long roomTypeId, HttpSession session) throws RoomNotFoundException, GuestNotFoundException {

        Reservation reservation = (Reservation) session.getAttribute("reservation");
        Guest leadguest = (Guest) session.getAttribute("leadguest");
        RoomType roomType = roomTypeRepository.findById(roomTypeId).orElseThrow(() -> new RoomNotFoundException(roomTypeId));
        model.addAttribute("roomTypeId", roomTypeId);

        List<RoomAsset> bookedRoomAssets = new ArrayList<>();
        List<RoomAsset> availableRoomAssets = new ArrayList<>();

        //other bookings by date range
        try {
            List<Booking> otrBookingsInDate = reservationRepository.findBookingsByDateRange(reservation.getBooking().getStartDate(),reservation.getBooking().getEndDate());
            for (Booking otrBookingInDate : otrBookingsInDate) {
                System.out.println("Found another Room Asset already booked in that date range with asset ID" + otrBookingInDate.getRoomAsset() + " and added it to array of booked rooms we can't book.");
                bookedRoomAssets.add(otrBookingInDate.getRoomAsset());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //room assets
        try {
            List<RoomAsset> RoomAssets = roomAssetRepository.findByRoomTypeId(roomTypeId);
            for (RoomAsset availableRoomAsset : RoomAssets) {
                System.out.println("All room assets by type " + availableRoomAsset.getroomasset_name() + " and added it to array of suitable rooms by type");
                availableRoomAssets.add(availableRoomAsset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //take the booked assets out of the roomsAssets of desired type and book something that's left
        try {

            availableRoomAssets.removeAll(bookedRoomAssets);
            for (RoomAsset roomAsset : availableRoomAssets) {
                System.out.println("Make the booking on the RoomAsset " + roomAsset.getroomasset_name() + " as matches request and is available.");
                booking.setRoomAsset(roomAsset);
                bookingRepository.save(booking);
                break; //run once
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Save reservation");
        reservationRepository.save(new Reservation(booking, leadguest));
        model.addAttribute("reservation", reservation);

        return "redirect:/list";
    }*/


    /*@RequestMapping(value = "bookingsanon/save", method = RequestMethod.POST)
    public String updateStrangerBooking( @ModelAttribute("booking")  Booking booking, Model model) throws BookingNotFoundException, GuestNotFoundException {
        System.out.println("NEVER RUN I redirect on in the saving of a new customer booking: " +booking.getleadguest_first_name() + " and guest" + reservationRepository.findGuestByBookingId(booking.getId()) +" !!!");
        bookingRepository.save(booking);
        return "redirect:/list";
    }*/

    /*@RequestMapping(value = "bookingsanon/add/{id}", method = RequestMethod.POST)
    public String addGuestFromSite(@PathVariable(value = "id") Long bookingId, @RequestParam("guestMissing") Long guestId,  Model model)  throws BookingNotFoundException, GuestNotFoundException{

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException(bookingId));
        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new GuestNotFoundException(guestId));
        System.out.println("saving of booking: " +booking.getleadguest_first_name() + "and guest" + guest.getGuest_first_name() + " ." );
        System.out.println("saving of booking: " +booking.getleadguest_first_name() + "and guest" + guest.getGuest_last_name() + " ." );
        reservationRepository.save(new Reservation(booking, guest));

        return "redirect:/bookingsanon/"+String.valueOf(bookingId);

    }*/

    // this chains in the deletion. but has never run or been tested, it's from the example code
    @RequestMapping("bookings/removeGuest/{id}")
    public String deleteReservation(@PathVariable(value = "id") Long bookingId, @RequestParam("id") Long guestId,  Model model) throws  BookingNotFoundException, GuestNotFoundException{
        Reservation aut = reservationRepository.findReservationByGuestAndBookingId(bookingId, guestId);
        reservationRepository.delete(aut);
        System.out.println("NEVER RUN We're deleting a booking so removeReservation guest ID:" +guestId + " and reservations " +aut );
        return "redirect:/bookings/" + String.valueOf(bookingId) ;

    }

}