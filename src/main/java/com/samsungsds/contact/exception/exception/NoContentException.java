package com.samsungsds.contact.exception.exception;

import com.samsungsds.contact.exception.ErrorType;

public class NoContentException  extends  ContactServerException{
    public NoContentException() {
        super(ErrorType.NO_CONTENT);
    }
}
