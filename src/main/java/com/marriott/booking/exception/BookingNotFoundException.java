package com.marriott.booking.exception;

public class BookingNotFoundException extends Exception{
    public BookingNotFoundException(long reservationId){
        super(String.format("Booking with id '%s' not found", reservationId));
    }

    public BookingNotFoundException(String ref){
        super(String.format("Booking with ref '%s' not found", ref));
    }
}
