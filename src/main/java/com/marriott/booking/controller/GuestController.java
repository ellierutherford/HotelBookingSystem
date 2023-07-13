package com.marriott.booking.controller;

import com.marriott.booking.exception.GuestDeleteException;
import com.marriott.booking.exception.CustomerNotFoundException;
import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.model.Customer;
import com.marriott.booking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GuestController {

    @Autowired
    CustomerRepository guestRepository;

    @RequestMapping({ "/guests"})
    public String viewGuestHomePage(Model model){
        List<Customer> listCustomers = guestRepository.findAll();
        model.addAttribute("listGuests", listCustomers);

        System.out.println("All the guests" );
        return "welcomeGuest";
    }

    @GetMapping("/guests/{id}")
    public String getGuestById(@PathVariable(value="id") Long guestId, Model model)
            throws CustomerNotFoundException, BookingNotFoundException{
        Customer customer = guestRepository.findById(guestId)/*add the guest to the model*/
                .orElseThrow(() -> new CustomerNotFoundException(guestId));
        model.addAttribute("guest", customer);
        System.out.println("Get Guests: " + customer.getId() + "With first name" + customer.getGuest_first_name() );

        return "editguestform";
    }

    // Create a Guest
    @RequestMapping("/newguest")
    public String createGuest(){
        return "guestform";
    }
    // Delete a Guest
    @RequestMapping("/deleteguest/{id}")
    public String deleteGuest(@PathVariable(value = "id") Long guestId, Model model) throws CustomerNotFoundException {
        try {
            Customer customer = guestRepository.findById(guestId)
                .orElseThrow(() -> new CustomerNotFoundException(guestId));
            System.out.println("Guest Deleted: " + customer.getId() + "With first name" + customer.getGuest_first_name() );
            guestRepository.delete(customer);
            return viewGuestHomePage(model); }
        catch (DataIntegrityViolationException e){
            throw new GuestDeleteException(e);
        }

    }
    // Save Created Guest
    @PostMapping("/guests")
    public String saveCreatedGuest(@ModelAttribute("guest") Customer customer, Model model){
        guestRepository.save(customer);
        System.out.println("Save Created Guest: " + customer.getId() + "With first name " + customer.getGuest_first_name() );
        return viewGuestHomePage(model);
    }

    @RequestMapping(value="/guests/save", method=RequestMethod.POST)
    public String updateGuest(@ModelAttribute("guest") Customer customer, Model model){
        guestRepository.save(customer);
        System.out.println("Updated : " + customer.getId() + "With first name " + customer.getGuest_first_name() );
        return viewGuestHomePage(model);
    }

}



