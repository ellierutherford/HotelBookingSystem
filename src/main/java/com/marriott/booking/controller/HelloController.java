package com.marriott.booking.controller;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marriott.booking.model.CheckEvent;
import com.marriott.booking.repository.CheckEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/postToSharePoint")
    public ResponseEntity<String> postToSharePoint(@RequestBody CheckEvent checkEvent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the JSON request body using the CheckEvent object
        String requestBody = "{\"Title\":\"" + checkEvent.getTitle() + "\","
                + "\"ActivityType\":\"" + checkEvent.getActivityType() + "\","
                + "\"Task\":\"none\","
                + "\"Location\":\"" + checkEvent.getLocation() + "\","
                + "\"ClientSite\":\"" + checkEvent.getClientSite() + "\","
                + "\"ActivityDate\":\"" + checkEvent.getActivityDate() + "\","
                + "\"ActivityDateTime\":\"" + checkEvent.getActivityDate() + "\"}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Define the SharePoint API URL
        String sharePointApiUrl = "https://laoisscaffolding.sharepoint.com/sites/TimeKeeping/_api/web/lists/GetByTitle('TimeTracker')/items";

        // Make the HTTP POST request to SharePoint
        ResponseEntity<String> response = restTemplate.postForEntity(sharePointApiUrl, requestEntity, String.class);

        return response;
    }



    @Autowired
    private RestTemplate restTemplate; // Autowire RestTemplate bean

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
        String location = "";
        String title = "";

        if (authentication instanceof AnonymousAuthenticationToken) {
            username = "THis wasn't processed, was an error";
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
                location = jsonNode.get("Location").asText();
                title = jsonNode.get("Title").asText();
                CheckEvent checkEvent = new CheckEvent();
                checkEvent.setEventTime(LocalDateTime.now());
                checkEvent.setTitle(title);
                checkEvent.setClientSite(clientSite);
                checkEvent.setActivityDate(LocalDateTime.now());
                checkEvent.setActivityType(activityType);
                checkEvent.setLocation(location);
                checkEventService.save(checkEvent);


                // Define the SharePoint API URL
                String sharePointApiUrl = "https://laoisscaffolding.sharepoint.com/sites/TimeKeeping/_api/web/lists/GetByTitle('TimeTracker')/items";

                // Create the JSON request body using the CheckEvent object
                String requestBody = "{\"Title\":\"" + checkEvent.getTitle() + "\","
                        + "\"ActivityType\":\"" + checkEvent.getActivityType() + "\","
                        + "\"Task\":\"none\","
                        + "\"Location\":\"" + checkEvent.getLocation() + "\","
                        + "\"ClientSite\":\"" + checkEvent.getClientSite() + "\","
                        + "\"ActivityDate\":\"" + checkEvent.getActivityDate() + "\","
                        + "\"ActivityDateTime\":\"" + checkEvent.getActivityDate() + "\"}";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

                // Make the HTTP POST request to SharePoint
                ResponseEntity<String> response = restTemplate.postForEntity(sharePointApiUrl, requestEntity, String.class);

                // Print the SharePoint response to the console
                System.out.println("SharePoint Response: " + response.getBody());




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
