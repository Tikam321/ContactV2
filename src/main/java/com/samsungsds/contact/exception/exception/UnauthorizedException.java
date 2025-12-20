package com.samsungsds.contact.exception.exception;

import com.samsungsds.contact.exception.ErrorType;

public class UnauthorizedException extends ContactServerException {
    public UnauthorizedException() {
        super(ErrorType.UNAUTHORIZED);
    }


}
