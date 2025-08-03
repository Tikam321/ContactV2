package com.samsungsds.contact.exception.exception;

import lombok.Getter;

@Getter
public class ContactServiceException extends RuntimeException {
    private final ErrorType errorType;
    private final String argument;

    public ContactServiceException(ErrorType errorType, String message) {
        super(errorType.getCode() + " " + errorType);
        this.errorType = errorType;
        this.argument = message;
    }

    public ContactServiceException(ErrorType errorType) {
        super(errorType.getCode() + " " + errorType);
        this.errorType = errorType;
        this.argument = "";
    }
}
