package com.marriott.booking.exception;

public class RoomAssetNotFoundException extends Exception{

    public RoomAssetNotFoundException(long room_id) {
        super(String.format("THIS ROOM ASSET IS NOT FOUND! '%s'", room_id));
    }

}
