package com.marriott.booking.controller;

import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.exception.RoomDeleteException;
import com.marriott.booking.exception.RoomNotFoundException;
import com.marriott.booking.model.RoomType;
import com.marriott.booking.repository.BookingRepository;
import com.marriott.booking.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    RoomTypeRepository roomRepository;


    // Get a Single Room
    @PreAuthorize("hasAuthority('ROLE_admin')")
    @GetMapping("/rooms/{id}")
    public String getRoomById(@PathVariable(value="id") Long roomId, Model model)
            throws RoomNotFoundException{
        RoomType room = roomRepository.findById(roomId)/*add the room to the model*/
                .orElseThrow(() -> new RoomNotFoundException(roomId));
        model.addAttribute("room", room);
        return "editroomform";
    }


    // See All Rooms on Homepage
    @PreAuthorize("hasAuthority('ROLE_admin')")
    @RequestMapping({ "/rooms"})
    public String viewRoomHomePage(Model model){
        List<RoomType> listRooms = roomRepository.findAll();
        model.addAttribute("listRooms", listRooms);
        return "welcomeRoom";
    }

    // Delete a Room
    @PreAuthorize("hasAuthority('ROLE_admin')")
    @RequestMapping("/deleteroom/{id}")
    public String deleteRoom(@PathVariable(value = "id") Long roomId, Model model) throws RoomNotFoundException{
        try {
            RoomType room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(roomId));
            roomRepository.delete(room);
            return viewRoomHomePage(model); }
        catch (DataIntegrityViolationException e){
            throw new RoomDeleteException(e);
        }

    }

    // Create a Room
    @PreAuthorize("hasAuthority('ROLE_admin')")
    @RequestMapping("/newroom")
    public String createRoom(){
        return "roomform";
    }
    // Save Created Room
    @PreAuthorize("hasAuthority('ROLE_admin')")
    @PostMapping("/rooms")
    public String saveCreatedRoom(@ModelAttribute("room") RoomType room, Model model){
        roomRepository.save(room);
        return viewRoomHomePage(model);
    }

    // Update a Room
    // Get Room By ID and open the editform

    // Save Updated Details
    @PreAuthorize("hasAuthority('ROLE_admin')")
    @RequestMapping(value="/rooms/save", method=RequestMethod.POST)
    public String updateRoom(@ModelAttribute("room") RoomType room, Model model){
        System.out.println("RoomType Controller updateRoom " );
        roomRepository.save(room);
        return viewRoomHomePage(model);
    }

}



