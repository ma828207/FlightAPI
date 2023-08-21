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
     *
     * @param noContentException
     * @return object of ResponseEntity having NoContentException details
     */
    @ExceptionHandler(value = NoContentException.class)
    public ResponseEntity<ExceptionResponse> handleNoContentException(NoContentException noContentException) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.toString());
        response.setErrorMessage(noContentException.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles the sort input exception with proper error code and error message.
     *
     * @param sortInputDataException
     * @return object of ResponseEntity having SortInputDataException details
     */
    @ExceptionHandler(value = SortInputDataException.class)
    public ResponseEntity<ExceptionResponse> handleSortInputDataException(SortInputDataException sortInputDataException) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.toString());
        response.setErrorMessage(sortInputDataException.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles the no result found exception with proper error code and error message.
     *
     * @param noResultFoundException
     * @return object of ResponseEntity having NoResultFoundException details
     */
    @ExceptionHandler(value = NoResultFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoResultException(NoResultFoundException noResultFoundException) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(HttpStatus.NOT_FOUND.toString());
        response.setErrorMessage(noResultFoundException.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles general exception with proper error code and error message.
     *
     * @param exception
     * @return returns object of ResponseEntity having Exception details
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


