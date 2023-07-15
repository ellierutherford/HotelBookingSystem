package com.marriott.booking.controller;

import com.marriott.booking.Utils;
import com.marriott.booking.exception.CardNotFoundException;
import com.marriott.booking.model.CreditCard;
import com.marriott.booking.model.User;
import com.marriott.booking.repository.CardRepository;
import com.marriott.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CardController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @GetMapping("/cards")
    public String addCardToBooking(@RequestParam Long userId, Model model) throws CardNotFoundException {
        List<CreditCard> cards = cardRepository.findByUserId(userId);
        model.addAttribute("cards", cards);
        if(cards.size()==0){
            return "cardform";
        }
        return "selectcard";
    }

    @GetMapping("/managecards")
    public String manageCardsForUser(Model model) throws CardNotFoundException {
        String username = Utils.getLoggedOnUserName();
        User u = userRepository.findByUsername(username);
        List<CreditCard> userCards = cardRepository.findByUserId(u.getUser_id());
        model.addAttribute("cards", userCards);
        return "managecards";
    }

}
