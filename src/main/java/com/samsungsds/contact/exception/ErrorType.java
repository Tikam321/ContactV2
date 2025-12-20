package com.samsungsds.contact.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorType {
    EXCEEDED_GROUP_LENGTH_LIMIT(HttpStatus.BAD_REQUEST, ErrorCode.EXCEEDED_GROUP_NAME_LENGTH,
            "exceeded group name length limit."),
    EXCEEDED_GROUP_MEMBER_LIMIT(HttpStatus.BAD_REQUEST, ErrorCode.EXCEEDED_GROUP_MEMBER_LIMIT,
            "exceeded group member limit."),
    EXISTED_GROUP(HttpStatus.BAD_REQUEST, ErrorCode.EXISTED_GROUP,
            "Group(#argument#) is already exist"
    ),
    NOT_EXISTED_GROUP(HttpStatus.BAD_REQUEST, ErrorCode.NOT_EXISTED_GROUP, "group does not exist"),
    NO_CONTENT(HttpStatus.BAD_REQUEST, ErrorCode.NO_CONTENT, "no content"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, ErrorCode.UNAUTHORIZED, "Unauthorized"),
    MISSING_ARGUMENT(HttpStatus.BAD_REQUEST, ErrorCode.MISSING_ARGUMENT, "missing argument"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR, "Please contact administrator");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorType(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
