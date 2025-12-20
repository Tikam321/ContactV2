package com.samsungsds.contact.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class ErrorMessage {
    private static final String DATETIME_FORMAT =  "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String ARGUMENT_TEXT = "#argument#";
    private final LocalDateTime serverTime;
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;
    private final String argument;

    public ErrorMessage(ErrorType errorType) {
        this.serverTime = LocalDateTime.now();
        this.httpStatus = errorType.getHttpStatus();
        this.errorCode = errorType.getCode();
        this.errorMessage = errorType.getMessage();
        this.argument = StringUtils.EMPTY;
    }
    public ErrorMessage(ErrorType errorType, String argument) {
        this.serverTime = LocalDateTime.now();
        this.httpStatus = errorType.getHttpStatus();
        this.errorCode = errorType.getCode();
        this.errorMessage = errorType.getMessage();
        this.argument = argument;
    }

    public String getResponseMessage() {
        return "[localhost" + "[" + DateTimeFormatter.ofPattern(DATETIME_FORMAT).format(this.serverTime) + "]"
                + "[" + errorCode + "]"
                + errorMessage;
    }

    public ResponseEntity<String> response() {
        return ResponseEntity.status(httpStatus)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .body(getResponseMessage());
    }
}
