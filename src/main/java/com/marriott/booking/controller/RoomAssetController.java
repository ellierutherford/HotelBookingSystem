package com.marriott.booking.controller;

import com.marriott.booking.exception.*;
import com.marriott.booking.model.*;
import com.marriott.booking.repository.RoomAssetRepository;
import com.marriott.booking.repository.ReservationRepository;
import com.marriott.booking.repository.BookingRepository;
import com.marriott.booking.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomAssetController {

    @Autowired
    RoomAssetRepository roomassetRepository;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @RequestMapping({ "/roomassets"})
    public String viewRoomAssetHomePage(Model model){
        System.out.println("1 Display view of all room assets called, seedings room assets and room types" );
        List<RoomAsset> roomasset = roomassetRepository.findAll();
        model.addAttribute("roomasset", roomasset);

        /*System.out.println("All the roomassets sent to view" );*/
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        model.addAttribute("roomTypes", roomTypes);
        System.out.println("All the roomtypes sent to view" );

        /*for (RoomAsset roomAsset : roomasset) {
            System.out.println(" with" + roomAsset.getroomasset_name() + "" );
            System.out.println(" with" + roomAsset.getroomasset_number() + "" );
        }*/


        /* List<Integer> bookingCounts = new ArrayList<>();
        for (RoomAsset roomasset : listRoomAssets) {
            try {
                List<Booking> bookings = roomassetRepository.findBookingByRoomAsset(roomasset);
                int bookingCount = bookings.size();
                bookingCounts.add(bookingCount);
            } catch (BookingNotFoundException e) {
                e.printStackTrace();
                bookingCounts.add(0);
            }
        }
        //this would sending the booking count for the rooms

        model.addAttribute("bookingCounts", bookingCounts);*/

        return "welcomeroomasset";
    }

    @GetMapping("/roomassets/{id}")
    public String getRoomAssetById(@PathVariable(value="id") Long roomassetId, Model model)
            throws RoomAssetNotFoundException, BookingNotFoundException{
        RoomAsset roomasset = roomassetRepository.findById(roomassetId)/*add the roomasset to the model*/
                .orElseThrow(() -> new RoomAssetNotFoundException(roomassetId));
        model.addAttribute("roomasset", roomasset);
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        model.addAttribute("roomTypes", roomTypes);
        System.out.println("Get RoomAssets: " + roomasset.getId() + "With name" + roomasset.getroomasset_name() );
        return "editroomassetform";
    }

    // Create a RoomAsset
    @RequestMapping("/newroomasset")
    public String createRoomAsset(Model model){
        System.out.println("Asset 1 createRoomAsset outputs attribute roomTypes to roomassetform" );
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        model.addAttribute("roomTypes", roomTypes);
        return "roomassetform";
    }

    // Save Created RoomAsset
    @PostMapping("/roomassets")
    //<form action="roomassets" method="post"> in roomassetform.jsp feeds //
    public String saveCreatedRoomAsset(@ModelAttribute("roomasset") RoomAsset roomasset,@RequestParam("roomTypeId") Long roomTypeId, Model model){
        try {
            RoomType roomtype = roomTypeRepository.findById(roomTypeId).orElseThrow(() -> new RoomNotFoundException(roomTypeId));
            roomasset.setroomasset_type(roomtype);
            roomassetRepository.save(roomasset);
            System.out.println("Asset 2Save Created With name " + roomasset.getroomasset_name() + "roomtype  :" + roomtype + "." );
        } catch (RoomNotFoundException e) {
            e.printStackTrace();
        }
        return viewRoomAssetHomePage(model);
    }




    @RequestMapping("/deleteroomasset/{id}")
    public String deleteRoomAsset(@PathVariable(value = "id") Long roomassetId, Model model) throws RoomAssetNotFoundException{
        try {RoomAsset roomasset = roomassetRepository.findById(roomassetId)
                .orElseThrow(() -> new RoomAssetNotFoundException(roomassetId));
            System.out.println("RoomAsset Deleted: " + roomasset.getId() + "With first name" + roomasset.getroomasset_name() );
            roomassetRepository.delete(roomasset);
            return viewRoomAssetHomePage(model); }
        catch (DataIntegrityViolationException e){
            throw new RoomAssetDeleteException(e);
        }

    }




    // Update a RoomAsset
    // Get RoomAsset By ID and open the editform

    // Save Updated Details
    @RequestMapping(value="/roomassets/save", method=RequestMethod.POST)
    public String updateRoomAsset(@ModelAttribute("roomasset") RoomAsset roomasset, Model model){
        roomassetRepository.save(roomasset);
        System.out.println("Updated : " + roomasset.getId() + "With  name " + roomasset.getroomasset_name() );
        return viewRoomAssetHomePage(model);
    }

}



