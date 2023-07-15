package com.marriott.booking.controller;
import com.marriott.booking.Utils;
import com.marriott.booking.exception.BookingNotFoundException;
import com.marriott.booking.exception.CustomerNotFoundException;
import com.marriott.booking.model.Booking;
import com.marriott.booking.model.Customer;
import com.marriott.booking.model.User;
import com.marriott.booking.repository.BookingRepository;
import com.marriott.booking.repository.CustomerRepository;
import com.marriott.booking.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/register")
    public String register(){
        return "registerDetails";
    }
    @PostMapping("/registerDetails")
    public String registerDetails(@ModelAttribute Customer customer, Model model) {
        customerRepository.save(customer);
        model.addAttribute("customer", customer);
        return "registerCredentials";
    }

    @PostMapping("/registerCredentials")
    public String registerCreds(@ModelAttribute User user) {
        // by default, add everyone who registers through the GUI as a user
        user.setRoles("user");
        userRepository.save(user);
        return "registerSuccess";
    }

    @GetMapping("/logoutsuccess")
    public String logout(){
        return "logoutSuccess";
    }

    @GetMapping("/myaccount")
    public String viewLoggedOnUserAccount(Model model) throws CustomerNotFoundException {
        User user = userRepository.findByUsername(Utils.getLoggedOnUserName());
        Customer customer = customerRepository.findByCustomerId(user.getUser_id());
        model.addAttribute("customer", customer);
        return "customerdetails";
    }

    @GetMapping("mybookings")
    public String viewReservations(Model model) throws BookingNotFoundException {
        User user = userRepository.findByUsername(Utils.getLoggedOnUserName());
        List<Booking> bookings = bookingRepository.findByUserId(user.getUser_id());
        model.addAttribute("bookings", bookings);
        return "userbookings";
    }

    @RequestMapping("/deleteRegistration")
    public String deleteRegistration(HttpServletRequest request) throws ServletException {
        User user = userRepository.findByUsername(Utils.getLoggedOnUserName());
        userRepository.delete(user);
        request.logout();
        return "deletionSuccess";
    }

    @RequestMapping("/unauthorized")
    public String notAuthorized(){
        return "unauthorized";
    }
}
