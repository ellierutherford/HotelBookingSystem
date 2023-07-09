package com.marriott.booking.controller;
import com.marriott.booking.model.User;
import com.marriott.booking.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    /*@GetMapping("/login")
    public String login(){
        return "login";
    }*/

    @PostMapping("/loginAction")
    public String loginAction(HttpServletRequest request, @ModelAttribute User user) {
        User u = userRepo.findByUsername(user.getUsername());
        if(u.getPassword().equals(user.getPassword())){

            return "loginSuccess";
        }
        else{
            return "loginFailure";
        }
    }


    @GetMapping("/test")
    public String test(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        return "test";
    }

    @GetMapping("/listusers")
    public String listUsers(Model model){
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "listusers";
    }
}
