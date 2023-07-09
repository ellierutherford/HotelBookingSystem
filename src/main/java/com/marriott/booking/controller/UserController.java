package com.marriott.booking.controller;
import com.marriott.booking.model.User;
import com.marriott.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration_form";
    }

    @PostMapping("/registration")
    public String register(Model model, @ModelAttribute User user) {
        userRepo.save(user);
        return "registerSuccess";
    }

    @GetMapping("/login")
    public String login(){
        return "welcome";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }


    @GetMapping("/listusers")
    public String listUsers(Model model){
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "listusers";
    }
}
