package com.marriott.booking.exception;

public class RoomNotFoundException extends Exception{

    public RoomNotFoundException(long room_id) {
        super(String.format("THIS ROOM type IS NOT FOUND! '%s'", room_id));
    }

}
