package com.samax.simpleCommerce.common.excption;

import lombok.Getter;
import org.springframework.http.HttpStatus;


public class ScHttpException extends RuntimeException {

    @Getter
    private final HttpStatus httpStatus;

    private final String message;


    public ScHttpException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
