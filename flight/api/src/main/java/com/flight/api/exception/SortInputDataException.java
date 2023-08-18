package com.flight.api.exception;

/**
 * Custom Exception to handle incorrect sort attributes.
 */
public class SortInputDataException extends Exception {
    public SortInputDataException(String msg) {
        super(msg);
    }
}

