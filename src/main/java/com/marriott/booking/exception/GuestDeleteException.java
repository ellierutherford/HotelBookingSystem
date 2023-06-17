package com.marriott.booking.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class GuestDeleteException extends DataIntegrityViolationException {
    private long booking_id;
    public GuestDeleteException(
            DataIntegrityViolationException e) {
        super(String.format("THIS Guest IS ASSOCIATED WITH A BOOKing! Cannot Delete!: '%s'", e));
    }

}
