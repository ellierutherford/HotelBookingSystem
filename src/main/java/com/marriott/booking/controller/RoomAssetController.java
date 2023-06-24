package com.marriott.booking.controller;

import com.marriott.booking.exception.RoomAssetDeleteException;
import com.marriott.booking.exception.RoomAssetNotFoundException;
import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.model.RoomAsset;
import com.marriott.booking.model.Booking;
import com.marriott.booking.repository.RoomAssetRepository;
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
public class RoomAssetController {

    @Autowired
    RoomAssetRepository roomassetRepository;

    @RequestMapping({ "/roomassets"})
    public String viewRoomAssetHomePage(Model model){
        List<RoomAsset> roomasset = roomassetRepository.findAll();
        model.addAttribute("roomasset", roomasset);

        System.out.println("All the roomassets" );
        /* List<Integer> bookCounts = new ArrayList<>();
        for (RoomAsset roomasset : listRoomAssets) {
            try {
                List<Booking> books = roomassetRepository.findBookingByRoomAsset(roomasset);
                int bookCount = books.size();
                bookCounts.add(bookCount);
            } catch (BookingNotFoundException e) {
                e.printStackTrace();
                bookCounts.add(0);
            }
        }


        model.addAttribute("bookCounts", bookCounts);*/

        return "welcomeroomasset";
    }

    @GetMapping("/roomassets/{id}")
    public String getRoomAssetById(@PathVariable(value="id") Long roomassetId, Model model)
            throws RoomAssetNotFoundException, BookingNotFoundException{
        RoomAsset roomasset = roomassetRepository.findById(roomassetId)/*add the roomasset to the model*/
                .orElseThrow(() -> new RoomAssetNotFoundException(roomassetId));
        model.addAttribute("roomasset", roomasset);
        System.out.println("Get RoomAssets: " + roomasset.getId() + "With name" + roomasset.getroomasset_name() );

        /*List<Booking> books = roomassetRepository.findBookingByRoomAsset(roomasset.getId()); find the books by the roomasset ID
        List<Booking> books = new ArrayList<>();
        for(Booking books: book){
            Booking book =roomassetRepository.findById(book)
                    .orElseThrow(() -> new BookingNotFoundException(book));
            books.add(book);
        }
        model.addAttribute("books", book);*/

        return "editroomassetform";
    }

    // Create a RoomAsset
    @RequestMapping("/newroomasset")
    public String createRoomAsset(){
        return "roomassetform";
    }
    // Delete a RoomAsset
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


    // Save Created RoomAsset
    @PostMapping("/roomassets")
    public String saveCreatedRoomAsset(@ModelAttribute("roomasset") RoomAsset roomasset, Model model){
        roomassetRepository.save(roomasset);
        System.out.println("Save Created RoomAsset: " + roomasset.getId() + "With name " + roomasset.getroomasset_name() );
        return viewRoomAssetHomePage(model);
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



