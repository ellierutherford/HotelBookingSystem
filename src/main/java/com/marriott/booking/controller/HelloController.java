package com.marriott.booking.controller;

import com.marriott.booking.model.CheckEvent;
import com.marriott.booking.repository.CheckEventService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class HelloController {

    private final CheckEventService checkEventService;

    public HelloController(CheckEventService checkEventService) {
        this.checkEventService = checkEventService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping("/post")
    public String postMessage(@RequestBody String message, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = "";
        if (authentication instanceof AnonymousAuthenticationToken) {
            username = "anon " + message;
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = "Auth" + userDetails + message;
        }

        // Create and save a CheckEvent record
        CheckEvent checkEvent = new CheckEvent();
        checkEvent.setEventTime(LocalDateTime.now());
        checkEvent.setTitle("YourTitle"); // Replace with the appropriate title
        checkEvent.setClientSite("YourClientSite"); // Replace with the appropriate client site
        checkEvent.setActivityDate(LocalDateTime.now()); // Replace with the appropriate activity date
        checkEvent.setActivityType("YourActivityType"); // Replace with the appropriate activity type
        checkEvent.setLocation("YourLocation"); // Replace with the appropriate location

        // Save the CheckEvent record
        checkEventService.save(checkEvent); // Use save() method provided by JpaRepository

        return "Received message: " + username;
    }
}
