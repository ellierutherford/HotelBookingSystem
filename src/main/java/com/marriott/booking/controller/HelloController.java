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


                String sharePointApiUrl = "https://laoisscaffolding.sharepoint.com/sites/TimeKeeping/_api/web/lists/GetByTitle('TimeTracker')/items";

                // Create the JSON request body using the CheckEvent object
                String requestBody = "{\"Title\":\"" + checkEvent.getTitle() + "\","
                        + "\"ActivityType\":\"" + checkEvent.getActivityType() + "\","
                        // ... (other fields)
                        + "\"ActivityDateTime\":\"" + checkEvent.getActivityDate() + "\"}";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ii1LSTNROW5OUjdiUm9meG1lWm9YcWJIWkdldyIsImtpZCI6Ii1LSTNROW5OUjdiUm9meG1lWm9YcWJIWkdldyJ9.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAvbGFvaXNzY2FmZm9sZGluZy5zaGFyZXBvaW50LmNvbUAxM2RiOWVmZS1jMjIzLTQyZTctODRjMi1mZGE2MzE2N2FmOTgiLCJpc3MiOiIwMDAwMDAwMS0wMDAwLTAwMDAtYzAwMC0wMDAwMDAwMDAwMDBAMTNkYjllZmUtYzIyMy00MmU3LTg0YzItZmRhNjMxNjdhZjk4IiwiaWF0IjoxNjkzOTAwMjg5LCJuYmYiOjE2OTM5MDAyODksImV4cCI6MTY5Mzk4Njk4OSwiaWRlbnRpdHlwcm92aWRlciI6IjAwMDAwMDAxLTAwMDAtMDAwMC1jMDAwLTAwMDAwMDAwMDAwMEAxM2RiOWVmZS1jMjIzLTQyZTctODRjMi1mZGE2MzE2N2FmOTgiLCJuYW1laWQiOiJmYWZjOGFlMi1hMmU5LTRlYjMtYTZjYi1iM2NhOGZjYzA0OWNAMTNkYjllZmUtYzIyMy00MmU3LTg0YzItZmRhNjMxNjdhZjk4Iiwib2lkIjoiZGRkMjMyNWItMmM4Yy00YzdkLTg0NzctMzU2OTMwYzRjNWQ4Iiwic3ViIjoiZGRkMjMyNWItMmM4Yy00YzdkLTg0NzctMzU2OTMwYzRjNWQ4IiwidHJ1c3RlZGZvcmRlbGVnYXRpb24iOiJmYWxzZSJ9.UuIzCz8ZdylThE7qrPBI5V6JYjHjtHZaW8oCgDnYh8-KHWn3H6Gs0MKm6yza3f3U7APt5rHPgZibIDBmlhLErGZEUJxUj4-d1_EkYkhBQPjIjmL_y6T0lfyA5rpHZ0nZtqGYhsoitL9NuJ0NqhEydiUrbKnjbSzOOfV307RKo9BUYarjw-aHAP4bZghKuQ-DQfggDURfChBVqSBVks87v5YRwcf5ARosbJd1Nbc4qwXAERTMhFRc0FyzHIWo61lEI7-Li5XpqMBmPyZCHM635UTH0ug1AOYlJ2vZEjFHyv5ZnJoq52kH_q0l57i9qyCjTfSTkLbNqtX5T1lXAnpoFw");
                HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
                System.out.println("Make the HTTP POST request to SharePoint");

                // Make the HTTP POST request to SharePoint
                ResponseEntity<String> response = restTemplate.postForEntity(sharePointApiUrl, requestEntity, String.class);

                if (response.getStatusCode().is2xxSuccessful()) {
                    // The request was successful
                    System.out.println("SharePoint Response: " + response.getBody());
                } else {
                    // Handle the error response (e.g., log it or throw an exception)
                    System.err.println("Error during SharePoint request. Status code: " + response.getStatusCode());
                    // You can log or throw an exception here to handle the error.
                }
            } catch (Exception e) {
                // Handle JSON parsing errors here
                System.err.println("Error parsing JSON: " + e.getMessage());
                // You can log or throw an exception here to handle the error.
            }
        }

        // Print the ActivityType to the console
        System.out.println("Received message: " + username);
        System.out.println("ActivityType: " + activityType);

        return "Received message: " + username;
    }
}



