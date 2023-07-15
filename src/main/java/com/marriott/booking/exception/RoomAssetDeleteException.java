package com.marriott.booking.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class RoomAssetDeleteException extends DataIntegrityViolationException {

    public RoomAssetDeleteException(
            DataIntegrityViolationException e) {
        super(String.format("THIS Room IS ASSOCIATED WITH A BOOKing! Cannot Delete!: '%s'", e));
    }

}
