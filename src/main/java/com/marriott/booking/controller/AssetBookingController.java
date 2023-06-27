package com.marriott.booking.controller;

import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.exception.AssetBookingDeleteException;
import com.marriott.booking.exception.AssetBookingNotFoundException;
import com.marriott.booking.model.AssetBooking;
import com.marriott.booking.repository.BookingRepository;
import com.marriott.booking.repository.AssetBookingRepository;
import com.marriott.booking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AssetBookingController {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    ReservationRepository ReservationRepository;
    @Autowired
    AssetBookingRepository assetbookingRepository;

    // Get a Single AssetBooking
    @GetMapping("/assetbookings/{id}")
    public String getAssetBookingById(@PathVariable(value="id") Long assetbookingId, Model model)
            throws AssetBookingNotFoundException, BookingNotFoundException{
        AssetBooking assetbooking = assetbookingRepository.findById(assetbookingId)/*add the assetbooking to the model*/
                .orElseThrow(() -> new AssetBookingNotFoundException(assetbookingId));
        model.addAttribute("assetbooking", assetbooking);

        System.out.println("AssetBooking Controller getAssetBookingById" );
        return "editassetbookingform";
    }


    // See All AssetBookings on Homepage
    @RequestMapping({ "/assetbookings"})
    public String viewAssetBookingHomePage(Model model){
        List<AssetBooking> listAssetBookings = assetbookingRepository.findAll();
        model.addAttribute("listAssetBookings", listAssetBookings);

        System.out.println("AssetBooking Controller viewAssetBookingHomePage" );

        return "welcomeAssetBooking";
    }

    // Delete a AssetBooking
    @RequestMapping("/deleteassetbooking/{id}")
    public String deleteAssetBooking(@PathVariable(value = "id") Long assetbookingId, Model model) throws AssetBookingNotFoundException{

        System.out.println("AssetBooking Controller deleteAssetBooking" );
        try {
            AssetBooking assetbooking = assetbookingRepository.findById(assetbookingId)
                .orElseThrow(() -> new AssetBookingNotFoundException(assetbookingId));
            assetbookingRepository.delete(assetbooking);
            return viewAssetBookingHomePage(model); }
        catch (DataIntegrityViolationException e){
            throw new AssetBookingDeleteException(e);
        }

    }

    // Create a AssetBooking
    @RequestMapping("/newassetbooking")
    public String createAssetBooking(){
        return "assetbookingform";
    }
    // Save Created AssetBooking
    @PostMapping("/assetbookings")
    public String saveCreatedAssetBooking(@ModelAttribute("assetbooking") AssetBooking assetbooking, Model model){
        System.out.println("AssetBooking Controller saveCreatedAssetBooking " );
        assetbookingRepository.save(assetbooking);
        return viewAssetBookingHomePage(model);
    }

    // Update a AssetBooking
    // Get AssetBooking By ID and open the editform

    // Save Updated Details
    @RequestMapping(value="/assetbookings/save", method=RequestMethod.POST)
    public String updateAssetBooking(@ModelAttribute("assetbooking") AssetBooking assetbooking, Model model){
        System.out.println("AssetBooking Controller updateAssetBooking " );
        assetbookingRepository.save(assetbooking);
        return viewAssetBookingHomePage(model);
    }

}



