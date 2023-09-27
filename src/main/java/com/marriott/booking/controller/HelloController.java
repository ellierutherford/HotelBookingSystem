package com.marriott.booking.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping("/post")
    public String postMessage(@RequestBody String message, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = "";
        if(authentication instanceof AnonymousAuthenticationToken){
            username = "anon "+message ;
        }
        else{
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = "Auth" + message;
        }




        return "Received message: " + username;
    }
}