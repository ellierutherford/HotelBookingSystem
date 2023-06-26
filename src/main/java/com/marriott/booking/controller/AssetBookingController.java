package com.marriott.booking.controller;

import com.marriott.booking.exception.*;
import com.marriott.booking.model.AssetBooking;
import com.marriott.booking.model.RoomAsset;
import com.marriott.booking.model.RoomType;
import com.marriott.booking.repository.AssetBookingRepository;
import com.marriott.booking.repository.RoomAssetRepository;
import com.marriott.booking.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AssetBookingController {

    @Autowired
    AssetBookingRepository assetbookingRepository;

    @Autowired
    RoomAssetRepository roomassetRepository;

    @RequestMapping({ "/assetbookings"})
    public String viewAssetBookingHomePage(Model model){

        System.out.println("1 Display view of all asset bookings called" );

        List<AssetBooking> assetbooking = assetbookingRepository.findAll();
        model.addAttribute("assetbooking", assetbooking);
        System.out.println("All the assetbooking sent to view" );


        List<RoomAsset> roomAssets = roomassetRepository.findAll();
        model.addAttribute("roomAssets", roomAssets);
        System.out.println("All the roomAssets sent to view" );


        return "welcomeassetbooking";
    }

    @GetMapping("/assetbookings/{id}")
    public String getAssetBookingById(@PathVariable(value="id") Long assetbookingId, Model model)
            throws AssetBookingNotFoundException, BookingNotFoundException{
        AssetBooking assetbooking = assetbookingRepository.findById(assetbookingId)/*add the assetbooking to the model*/
                .orElseThrow(() -> new AssetBookingNotFoundException(assetbookingId));
        model.addAttribute("assetbooking", assetbooking);

        List<RoomAsset> roomAssets = roomassetRepository.findAll();
        model.addAttribute("roomAssets", roomAssets);
        System.out.println("Get AssetBookings: " + assetbooking.getId() + " ."  );

        return "editassetbookingform";
    }

    // Create a AssetBooking
    @RequestMapping("/newassetbooking")
    public String createAssetBooking(Model model){
        System.out.println("Asset 1 createAssetBooking outputs attribute roomTypes to assetbookingform" );
        List<RoomAsset> roomAssets = roomassetRepository.findAll();
        model.addAttribute("roomAssets", roomAssets);
        return "assetbookingform";
    }

    // Save Created AssetBooking
    @PostMapping("/assetbookings")
    //<form action="assetbookings" method="post"> in assetbookingform.jsp feeds //
    public String saveCreatedAssetBooking(@ModelAttribute("assetbooking") AssetBooking assetbooking,@RequestParam("roomAssetId") Long roomAssetId, Model model){


            assetbookingRepository.save(assetbooking);

        return viewAssetBookingHomePage(model);
    }




    @RequestMapping("/deleteassetbooking/{id}")
    public String deleteAssetBooking(@PathVariable(value = "id") Long assetbookingId, Model model) throws AssetBookingNotFoundException{
        try {AssetBooking assetbooking = assetbookingRepository.findById(assetbookingId)
                .orElseThrow(() -> new AssetBookingNotFoundException(assetbookingId));
            System.out.println("AssetBooking Deleted: " + assetbooking.getId() + "With asset booking date" + assetbooking.getAssetbooking_date() );
            assetbookingRepository.delete(assetbooking);
            return viewAssetBookingHomePage(model); }
        catch (DataIntegrityViolationException e){
            throw new AssetBookingDeleteException(e);
        }

    }




    // Update a AssetBooking
    // Get AssetBooking By ID and open the editform

    // Save Updated Details
    @RequestMapping(value="/assetbookings/save", method=RequestMethod.POST)
    public String updateAssetBooking(@ModelAttribute("assetbooking") AssetBooking assetbooking, Model model){
        assetbookingRepository.save(assetbooking);
        System.out.println("Updated : " + assetbooking.getId() + "With asset booking date" + assetbooking.getAssetbooking_date() );
        return viewAssetBookingHomePage(model);
    }

}



