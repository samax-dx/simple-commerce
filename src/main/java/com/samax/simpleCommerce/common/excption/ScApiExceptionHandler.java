package com.samax.simpleCommerce.common.excption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintStream;


@ControllerAdvice
public class ScApiExceptionHandler {

    private final PrintStream errorOutputStream;


    public ScApiExceptionHandler(@Autowired(required = false) PrintStream errorOutputStream) {
        this.errorOutputStream = errorOutputStream == null ? System.err : errorOutputStream;
    }

    @ExceptionHandler(ScHttpException.class)
    public ResponseEntity<?> handleClientException(ScHttpException exception) {
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        exception.printStackTrace(errorOutputStream);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("unknown error");
    }

}
