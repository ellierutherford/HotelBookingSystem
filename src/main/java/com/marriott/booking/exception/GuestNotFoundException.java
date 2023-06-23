package com.marriott.booking.exception;

public class GuestNotFoundException extends Exception{
    private long author_id;
    public GuestNotFoundException(long guest_id) {
        super(String.format("THIS GUEST IS NOT FOUND! '%s'", guest_id));
    }

}
