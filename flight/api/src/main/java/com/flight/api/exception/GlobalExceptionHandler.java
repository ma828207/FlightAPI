package com.flight.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * This class is used to handle exceptions from all the controllers across whole application in one global handling component.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method handles the No Content exception with proper error code and error message.
     * @param noContentException
     * @return returns object of ResponseEntity having error details
     */
    @ExceptionHandler(value = NoContentException.class)
    public ResponseEntity<ExceptionResponse> handleNoContentException(NoContentException noContentException) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(HttpStatus.NOT_FOUND.toString());
        response.setErrorMessage(noContentException.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles general exception with proper error code and error message.
     * @param exception
     * @return returns object of ResponseEntity having error details
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception exception) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.toString());
        response.setErrorMessage(exception.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}


