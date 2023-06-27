package com.marriott.booking.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class RoomAssetDeleteException extends DataIntegrityViolationException {
    private long booking_id;
    public RoomAssetDeleteException(
            DataIntegrityViolationException e) {
        super(String.format("THIS Room IS ASSOCIATED WITH A BOOKing! Cannot Delete!: '%s'", e));
    }

}
