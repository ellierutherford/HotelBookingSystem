package com.marriott.booking.controller;

import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.exception.RoomDeleteException;
import com.marriott.booking.exception.RoomNotFoundException;
import com.marriott.booking.model.RoomType;
import com.marriott.booking.repository.BookingRepository;
import com.marriott.booking.repository.RoomtypeRepository;
import com.marriott.booking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoomController {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    ReservationRepository ReservationRepository;
    @Autowired
    RoomtypeRepository roomRepository;

    // Get a Single Room
    @GetMapping("/rooms/{id}")
    public String getRoomById(@PathVariable(value="id") Long roomId, Model model)
            throws RoomNotFoundException, BookingNotFoundException{
        RoomType room = roomRepository.findById(roomId)/*add the room to the model*/
                .orElseThrow(() -> new RoomNotFoundException(roomId));
        model.addAttribute("room", room);

        System.out.println("RoomType Controller getRoomById" );
        return "editroomform";
    }


    // See All Rooms on Homepage
    @RequestMapping({ "/rooms"})
    public String viewRoomHomePage(Model model){
        List<RoomType> listRooms = roomRepository.findAll();
        model.addAttribute("listRooms", listRooms);

        System.out.println("RoomType Controller viewRoomHomePage" );

        return "welcomeRoom";
    }

    // Delete a Room
    @RequestMapping("/deleteroom/{id}")
    public String deleteRoom(@PathVariable(value = "id") Long roomId, Model model) throws RoomNotFoundException{

        System.out.println("RoomType Controller deleteRoom" );
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
    @RequestMapping("/newroom")
    public String createRoom(){
        return "roomform";
    }
    // Save Created Room
    @PostMapping("/rooms")
    public String saveCreatedRoom(@ModelAttribute("room") RoomType room, Model model){
        System.out.println("RoomType Controller saveCreatedRoom " );
        roomRepository.save(room);
        return viewRoomHomePage(model);
    }

    // Update a Room
    // Get Room By ID and open the editform

    // Save Updated Details
    @RequestMapping(value="/rooms/save", method=RequestMethod.POST)
    public String updateRoom(@ModelAttribute("room") RoomType room, Model model){
        System.out.println("RoomType Controller updateRoom " );
        roomRepository.save(room);
        return viewRoomHomePage(model);
    }

}



