package com.flight.api.exception;

/**
 * Custom Exception to handle if Resource Not Found
 */
public class NoContentException extends Exception {
    public NoContentException(String msg) {
        super(msg);
    }
}


