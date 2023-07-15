package com.marriott.booking.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class RoomDeleteException extends DataIntegrityViolationException {
    public RoomDeleteException(
            DataIntegrityViolationException e) {
        super(String.format("THIS Room type IS ASSOCIATED WITH A BOOKing! Cannot Delete!: '%s'", e));
    }

}
