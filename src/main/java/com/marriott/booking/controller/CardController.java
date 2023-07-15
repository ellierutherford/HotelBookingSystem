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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
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
            return "cardformbooking";
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

    @GetMapping("/newcard")
    public String addCardForUser(){
        return "cardformuser";
    }

    @GetMapping("/editcardform")
    public String editCardForm(Model model, @RequestParam Long cardId) throws CardNotFoundException{
        CreditCard card = cardRepository.findByCardId(cardId);
        User user = userRepository.findByUsername(Utils.getLoggedOnUserName());
        if(!card.getUserId().equals(user.getUser_id())) {
            return "redirect:/unauthorized";
        }
        model.addAttribute("card", card);
        return "editcardform";
    }

    @PostMapping("/editcard")
    public String saveCardEdits(@ModelAttribute("card") CreditCard card){
        cardRepository.save(card);
        return "redirect:/managecards";
    }

    @PostMapping("/savecard")
    public String saveCardForUser(@ModelAttribute("card") CreditCard card){
        String username = Utils.getLoggedOnUserName();
        User user = userRepository.findByUsername(username);
        card.setUserId(user.getUser_id());
        cardRepository.save(card);
        return "redirect:/managecards";
    }

}
