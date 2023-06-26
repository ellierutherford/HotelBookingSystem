package com.marriott.booking.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class AssetBookingDeleteException extends DataIntegrityViolationException {
    private long booking_id;
    public AssetBookingDeleteException(
            DataIntegrityViolationException e) {
        super(String.format("THIS Asset IS ASSOCIATED WITH A BOOKing! Cannot Delete!: '%s'", e));
    }

}
