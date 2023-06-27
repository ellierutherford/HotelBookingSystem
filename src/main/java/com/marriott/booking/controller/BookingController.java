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

    @Autowired
    AssetBookingRepository assetBookingRepository;

    // Show available rooms to book
    @GetMapping("/allavailable")
    public String showAvailableRooms(@RequestParam("start_date")
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date,
                                     @RequestParam("end_date") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
                                     LocalDate end_date, Model model)
            throws RoomNotFoundException{
        List<Long> room_ids = roomAssetRepository.findAvailableRooms(start_date, end_date);
        List<RoomAsset> availableRooms = new ArrayList<RoomAsset>();
        for (Long room_id : room_ids) {
            RoomAsset room = roomAssetRepository.findById(room_id).orElseThrow(() -> new RoomNotFoundException(room_id));
            availableRooms.add(room);
        }
        model.addAttribute("rooms", availableRooms);
        return "room";
    }

    // Get a Single Booking
    @GetMapping("/bookings/{id}")
    public String getBookingById(@PathVariable(value="id") Long bookingId, Model model)
            throws BookingNotFoundException, GuestNotFoundException{
        Booking booking = bookingRepository.findById(bookingId)/*add the booking to the model*/
                .orElseThrow(() -> new BookingNotFoundException(bookingId));
        model.addAttribute("booking", booking);

        List<Guest> guests = reservationRepository.findGuestByBookingId(booking.getId());
        //System.out.println("2GETsingle booking by IDing a Single Booking for: " +booking.getleadguest_first_name() +" and guest its guest" + reservationRepository.findGuestByBookingId(booking.getId()));

        model.addAttribute("listguests", guests);
        model.addAttribute("missingGuests", reservationRepository.findGuestsNotInBooking(booking.getId()));
        //System.out.println("2GETSingle We can send the guests that are not in this booking to edit screen and they are: " + reservationRepository.findGuestsNotInBooking(booking.getId()) +" !!!");

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
        List<RoomType> listroomTypes = roomTypeRepository.findAll();
        model.addAttribute("listroomTypes", listroomTypes);
        return "bookingform";
    }
    @PostMapping("/bookings")
    public String saveCreatedBooking(@ModelAttribute("booking") Booking booking, @RequestParam("guestIds") Long[] guestIds, @RequestParam("listroomType") Long listroomType, Model model) throws GuestNotFoundException {
        //make array of our dates
        LocalDate startDate = booking.getStartDate();
        LocalDate endDate = booking.getEndDate();
        List<LocalDate> bookingDates = new ArrayList<>();

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            bookingDates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        try {
            List<RoomAsset> roomAssets = roomAssetRepository.findByRoomTypeId(listroomType);
            for (RoomAsset roomAsset : roomAssets) {
                System.out.println("Setting bookingRoomAsset to" + roomAsset.getroomasset_name() + " as matches request and is believed to be available.") ;
                booking.setRoomAsset(roomAsset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //write out all the dates of the booking to reserve the room
        for (LocalDate date : bookingDates) {
            AssetBooking assetbooking = new AssetBooking(booking.getRoomAsset(), date);
            assetBookingRepository.save(assetbooking);
            System.out.println("Added Reserved Booking Date: " + date);
        }

        bookingRepository.save(booking);
        for (Long guestId : guestIds) {
            Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new GuestNotFoundException(guestId));
            Reservation reservation = new Reservation(booking, guest);
            reservationRepository.save(reservation);
            System.out.println("4 Added guest " + guestId + " to booking " + booking.getId() + " on room asset" + booking.getRoomAsset() );
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

        System.out.println("1 ID Action Booking Deleted: " + booking.getId() + "With " + booking.getleadguest_first_name() );
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
        //need to send out roomtypes, full list for drop down

        return "newguestbooking";
    }
    @PostMapping("/newguestbookings")
    public String saveCreatedStrangerBooking(@ModelAttribute("booking") Booking booking,  Model model, HttpSession session) throws BookingNotFoundException, GuestNotFoundException {

        System.out.println("New unknown booker with Name: " + booking.getleadguest_first_name() + " " + booking.getleadguest_last_name() +" .");
        System.out.println("Booking Startdate: " + booking.getStartDate() + ". ");
        System.out.println("Booking Enddate: " + booking.getEndDate() + ". ");

        List<RoomType> listroomTypes = roomTypeRepository.findAll();
        model.addAttribute("listroomTypes", listroomTypes);

        bookingRepository.save(booking);

        Guest guest = new Guest();
        guest.setGuest_first_name(booking.getleadguest_first_name());
        guest.setGuest_last_name(booking.getleadguest_last_name());
        guestRepository.save(guest);

        Reservation reservation = new Reservation(booking, guest);
        reservationRepository.save(reservation);

        session.setAttribute("reservation", reservation);

        System.out.println("3a Save Created New Guest: " + guest.getId() + "With first name " + guest.getGuest_first_name() + "With last name ." + guest.getGuest_last_name() );
        model.addAttribute("guest", guest);
        return "newguestbookingstep2";
    }

    @PostMapping("/newguestbookingsstep2")
    public String saveCreatedStrangerBookingStep2(@ModelAttribute("booking") Booking booking, Model model, @RequestParam("listroomTypes") Long[] roomTypeIds, HttpSession session) throws RoomNotFoundException, GuestNotFoundException {

        Reservation reservation = (Reservation) session.getAttribute("reservation");
        System.out.println("New anon booking FIRSTNAME: " + booking.getleadguest_first_name() + " .");
        System.out.println("New anon booking LASTNAME: " + booking.getleadguest_last_name() + " .");
        System.out.println("STARTDATE" + booking.getStartDate() + ". ");
        System.out.println("ENDDATE" + booking.getEndDate() + ". ");
        //lets get available roomtypes for their dates by looking for roomtypes that have room assets that have Null for each date in between reservation.start and reservation.end

        for (Long roomTypeId : roomTypeIds) {
            //Find the room type from the room type ID
            RoomType roomType = roomTypeRepository.findById(roomTypeId).orElseThrow(() -> new RoomNotFoundException(roomTypeId));
            System.out.println("Attempt set room type to" + roomType.getRoom_name() + " on booking ID" + booking.getId());
            /*should run once*/
            model.addAttribute("roomTypeId", roomTypeId);
        }

        System.out.println("............redirect on saving of a brand new booking for this asset" + reservation.getBooking().getRoomAsset() + ". ");



        model.addAttribute("reservation", reservation);

        return "newguestbookings/save";
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