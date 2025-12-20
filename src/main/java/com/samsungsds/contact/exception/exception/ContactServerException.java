package com.samsungsds.contact.exception.exception;

import com.samsungsds.contact.exception.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ContactServerException  extends  RuntimeException{
    private final ErrorType errorType;
    private final String argument;

    public ContactServerException(ErrorType errorType) {
        super(errorType.getCode() + " " + errorType);
        this.errorType = errorType;
        this.argument = "";
    }

    public ContactServerException(ErrorType errorType, String argument) {
        super(errorType.getCode() + " " + errorType);
        this.errorType = errorType;
        this.argument = argument;
    }


}
