package com.samsungsds.contact.exception;

import com.samsungsds.contact.exception.exception.ContactServerException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
public class ExceptionResponseHandler {
    @ExceptionHandler(ContactServerException.class)
    protected ResponseEntity<String> handleContactServerException(ContactServerException e) {
        log.warn(e.getMessage(), e);
        return new ErrorMessage(e.getErrorType(), e.getArgument()).response();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<String> handleMissingParameterException(MissingServletRequestParameterException e) {
        log.warn(e.getMessage(), e);
        return new ErrorMessage(ErrorType.MISSING_ARGUMENT, e.getMessage()).response();
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    protected ResponseEntity<String> handleEmptyParameterException(HandlerMethodValidationException e) {
        log.warn(e.getMessage(), e);
        return new ErrorMessage(ErrorType.MISSING_ARGUMENT, e.getMessage() + "jakarata exceptoin").response();
    }

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<String> handleEmptyParameterException(Throwable e) {
        log.warn(e.getMessage(), e);
        return new ErrorMessage(ErrorType.INTERNAL_SERVER_ERROR, e.getMessage()).response();
    }
}
