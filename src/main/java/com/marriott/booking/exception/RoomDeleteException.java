package com.marriott.booking.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class RoomDeleteException extends DataIntegrityViolationException {
    private long booking_id;
    public RoomDeleteException(
            DataIntegrityViolationException e) {
        super(String.format("THIS Room IS ASSOCIATED WITH A BOOKing! Cannot Delete!: '%s'", e));
    }

}
