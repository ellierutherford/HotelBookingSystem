package com.marriott.booking.controller;
import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.exception.CustomerNotFoundException;
import com.marriott.booking.model.Booking;
import com.marriott.booking.model.BookingStatus;
import com.marriott.booking.model.Customer;
import com.marriott.booking.model.User;
import com.marriott.booking.repository.BookingRepository;
import com.marriott.booking.repository.CustomerRepository;
import com.marriott.booking.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // helper method
    private String getLoggedOnUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
    @GetMapping("/register")
    public String register(){
        return "registerDetails";
    }
    @PostMapping("/registerDetails")
    public String registerDetails(@ModelAttribute Customer user, Model model) {
        customerRepository.save(user);
        model.addAttribute("userId", user.getId());
        return "registerCredentials";
    }

    @PostMapping("/registerCredentials")
    public String registerCreds(@ModelAttribute User user, @RequestParam Long userId) {
        // by default, add everyone who registers through the GUI as a user
        user.setRoles("user");
        user.setUser_id(userId);
        userRepository.save(user);
        return "registerSuccess";
    }

    @GetMapping("/logoutsuccess")
    public String logout(){
        return "logoutSuccess";
    }

    @GetMapping("/myaccount")
    public String viewLoggedOnUserAccount(Model model) throws CustomerNotFoundException {
        User user = userRepository.findByUsername(getLoggedOnUser());
        Customer customer = customerRepository.findByCustomerId(user.getUser_id());
        model.addAttribute("customer", customer);
        return "customerdetails";
    }

    @GetMapping("mybookings")
    public String viewReservations(Model model) throws BookingNotFoundException {
        User user = userRepository.findByUsername(getLoggedOnUser());
        List<Booking> bookings = bookingRepository.findByUserId(user.getUser_id());
        model.addAttribute("bookings", bookings);
        return "userbookings";
    }

    @RequestMapping("/deleteRegistration")
    public String deleteRegistration(HttpServletRequest request) throws ServletException {
        User user = userRepository.findByUsername(getLoggedOnUser());
        userRepository.delete(user);
        request.logout();
        return "deletionSuccess";
    }
}
