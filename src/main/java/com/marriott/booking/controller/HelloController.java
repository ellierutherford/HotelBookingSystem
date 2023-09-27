package com.marriott.booking.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        String activityType = "";
        String clientSite = "";

        if (authentication instanceof AnonymousAuthenticationToken) {
            username = "anon " + message;
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = "Auth" + userDetails + message;

            try {
                // Parse the JSON message
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(message);

                // Extract the ActivityType value
                activityType = jsonNode.get("ActivityType").asText();
                clientSite = jsonNode.get("ClientSite").asText();

                // Create and save a CheckEvent record
                CheckEvent checkEvent = new CheckEvent();
                checkEvent.setEventTime(LocalDateTime.now());
                checkEvent.setTitle("YourTitle"); // Replace with the appropriate title
                checkEvent.setClientSite(clientSite); // Replace with the appropriate client site
                checkEvent.setActivityDate(LocalDateTime.now()); // Replace with the appropriate activity date
                checkEvent.setActivityType(activityType); // Set the extracted ActivityType
                checkEvent.setLocation("YourLocation"); // Replace with the appropriate location

                // Save the CheckEvent record
                checkEventService.save(checkEvent); // Use save() method provided by JpaRepository
            } catch (Exception e) {
                // Handle JSON parsing errors here
            }
        }

        // Print the ActivityType to the console
        System.out.println("Received message: " + username);
        System.out.println("ActivityType: " + activityType);

        return "Received message: " + username;
    }


}
