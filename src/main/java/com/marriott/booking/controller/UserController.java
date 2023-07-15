package com.marriott.booking.controller;
import com.marriott.booking.model.Customer;
import com.marriott.booking.model.User;
import com.marriott.booking.repository.CustomerRepository;
import com.marriott.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

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
}
