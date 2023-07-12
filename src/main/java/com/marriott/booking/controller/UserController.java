package com.marriott.booking.controller;
import com.marriott.booking.model.User;
import com.marriott.booking.model.UserInfo;
import com.marriott.booking.repository.UserInfoRepository;
import com.marriott.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @GetMapping("/register")
    public String register(){
        return "registerDetails";
    }
    @PostMapping("/registerDetails")
    public String registerDetails(@ModelAttribute UserInfo userInfo, Model model) {
        userInfoRepository.save(userInfo);
        model.addAttribute("userId", userInfo.getId());
        return "registerCredentials";
    }

    @PostMapping("/registerCredentials")
    public String registerCreds(@ModelAttribute User user, @RequestParam Long userId) {
        // by default, add everyone who registers through the GUI as a user
        user.setRoles("user");
        user.setUser_id(userId);
        userRepo.save(user);
        return "registerSuccess";
    }

    @GetMapping("/test")
    public String test(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if(authentication instanceof AnonymousAuthenticationToken){
            username = "anonymous";
        }
        else{
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }
        model.addAttribute("username", username);

        return "test";
    }

    @GetMapping("/logoutsuccess")
    public String logout(){
        return "logoutSuccess";
    }
}
