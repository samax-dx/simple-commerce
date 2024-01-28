package com.samax.simpleCommerce.common.excption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ScApiExceptionHandler {

    @ExceptionHandler(ScClientException.class)
    public ResponseEntity<?> handleClientException(ScClientException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        Object[] messageArguments = exception.getDetailMessageArguments();
        String validationErrorMessage = messageArguments != null && messageArguments.length > 1 ? (String) messageArguments[1] : "invalid input";
        return ResponseEntity.status(exception.getStatusCode()).body(validationErrorMessage);
    }

}
