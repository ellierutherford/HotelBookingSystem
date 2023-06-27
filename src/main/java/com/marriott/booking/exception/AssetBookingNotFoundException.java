package com.marriott.booking.exception;

public class AssetBookingNotFoundException extends Exception{
    private long room_id;
    public AssetBookingNotFoundException(long room_id) {
        super(String.format("THIS  ASSET booking IS NOT FOUND! '%s'", room_id));
    }

}
