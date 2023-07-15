package com.marriott.booking.controller;

import com.marriott.booking.exception.*;
import com.marriott.booking.model.*;
import com.marriott.booking.repository.RoomAssetRepository;
import com.marriott.booking.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoomAssetController {

    @Autowired
    RoomAssetRepository roomassetRepository;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @RequestMapping({ "/roomassets"})
    public String viewRoomAssetHomePage(Model model){
        List<RoomAsset> roomasset = roomassetRepository.findAll();
        model.addAttribute("roomasset", roomasset);
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        model.addAttribute("roomTypes", roomTypes);
        return "welcomeroomasset";
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @GetMapping("/roomassets/{id}")
    public String getRoomAssetById(@PathVariable(value="id") Long roomassetId, Model model)
            throws RoomAssetNotFoundException{
        RoomAsset roomasset = roomassetRepository.findById(roomassetId)
                .orElseThrow(() -> new RoomAssetNotFoundException(roomassetId));
        model.addAttribute("roomasset", roomasset);

        List<RoomType> roomTypes = roomTypeRepository.findAll();
        model.addAttribute("roomTypes", roomTypes);
        return "editroomassetform";
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    // Create a RoomAsset
    @RequestMapping("/newroomasset")
    public String createRoomAsset(Model model){
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        model.addAttribute("roomTypes", roomTypes);
        return "roomassetform";
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    // Save Created RoomAsset
    @PostMapping("/roomassets")
    public String saveCreatedRoomAsset(@ModelAttribute("roomasset") RoomAsset roomasset,@RequestParam("roomTypeId") Long roomTypeId, Model model){
        try {
            RoomType roomtype = roomTypeRepository.findById(roomTypeId).orElseThrow(() -> new RoomNotFoundException(roomTypeId));
            roomasset.setroomasset_type(roomtype);
            roomassetRepository.save(roomasset);
        } catch (RoomNotFoundException e) {
            e.printStackTrace();
        }
        return viewRoomAssetHomePage(model);
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @RequestMapping("/deleteroomasset/{id}")
    public String deleteRoomAsset(@PathVariable(value = "id") Long roomassetId, Model model) throws RoomAssetNotFoundException{
        try {
            RoomAsset roomasset = roomassetRepository.findById(roomassetId)
                .orElseThrow(() -> new RoomAssetNotFoundException(roomassetId));
            roomassetRepository.delete(roomasset);
            return viewRoomAssetHomePage(model); }
        catch (DataIntegrityViolationException e){
            throw new RoomAssetDeleteException(e);
        }

    }

    // Update a RoomAsset
    // Get RoomAsset By ID and open the editform

    // Save Updated Details
    @PreAuthorize("hasAuthority('ROLE_admin')")
    @RequestMapping(value="/roomassets/save", method=RequestMethod.POST)
    public String updateRoomAsset(@ModelAttribute("roomasset") RoomAsset roomasset, Model model){
        roomassetRepository.save(roomasset);
        return viewRoomAssetHomePage(model);
    }

}



