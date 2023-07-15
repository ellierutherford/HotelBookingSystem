package com.marriott.booking.exception;

public class CardNotFoundException extends Exception{

    public CardNotFoundException(long id) {
        super(String.format("Card with id '%s' not found", id));
    }

}
