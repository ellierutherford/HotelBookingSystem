package com.marriott.booking.exception;

public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(long guest_id) {
        super(String.format("THIS GUEST IS NOT FOUND! '%s'", guest_id));
    }

}
