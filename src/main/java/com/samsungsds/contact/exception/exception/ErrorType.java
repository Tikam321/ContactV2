package com.samsungsds.contact.exception.exception;

import lombok.AllArgsConstructor;
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
    NOT_EXISTED_GROUP(HttpStatus.BAD_REQUEST, ErrorCode.NOT_EXISTED_GROUP, "group does not exist");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorType(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
