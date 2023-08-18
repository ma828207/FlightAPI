package com.flight.api.exception;

/**
 * Custom Exception to handle if Result Not Found
 */
public class NoResultFoundException extends Exception {
    public NoResultFoundException(String msg) {
        super(msg);
    }
}



