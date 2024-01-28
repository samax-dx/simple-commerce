package com.samax.simpleCommerce.excption;

import lombok.Getter;
import org.springframework.http.HttpStatus;


public class ScClientException extends RuntimeException {

    @Getter
    private final HttpStatus httpStatus;

    private final String message;


    public ScClientException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
