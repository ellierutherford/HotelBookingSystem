package com.marriott.booking.controller;

import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.exception.RoomDeleteException;
import com.marriott.booking.exception.RoomNotFoundException;
import com.marriott.booking.model.Booking;
import com.marriott.booking.model.Room;
import com.marriott.booking.repository.BookingRepository;
import com.marriott.booking.repository.RoomRepository;
import com.marriott.booking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    ReservationRepository ReservationRepository;
    @Autowired
    RoomRepository roomRepository;

    // Get a Single Room
    @GetMapping("/rooms/{id}")
    public String getRoomById(@PathVariable(value="id") Long roomId, Model model)
            throws RoomNotFoundException, BookingNotFoundException{
        Room room = roomRepository.findById(roomId)/*add the room to the model*/
                .orElseThrow(() -> new RoomNotFoundException(roomId));
        model.addAttribute("room", room);


        return "editroomform";
    }


    // See All Rooms on Homepage
    @RequestMapping({ "/rooms"})
    public String viewRoomHomePage(Model model){
        List<Room> listRooms = roomRepository.findAll();
        model.addAttribute("listRooms", listRooms);



        return "welcomeRoom";
    }

    // Delete a Room
    @RequestMapping("/deleteroom/{id}")
    public String deleteRoom(@PathVariable(value = "id") Long roomId, Model model) throws RoomNotFoundException{
        try {Room room = roomRepository.findById(roomId)
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
    public String saveCreatedRoom(@ModelAttribute("room") Room room, Model model){
        roomRepository.save(room);
        return viewRoomHomePage(model);
    }

    // Update a Room
    // Get Room By ID and open the editform

    // Save Updated Details
    @RequestMapping(value="/rooms/save", method=RequestMethod.POST)
    public String updateRoom(@ModelAttribute("room") Room room, Model model){
        roomRepository.save(room);
        return viewRoomHomePage(model);
    }

}



