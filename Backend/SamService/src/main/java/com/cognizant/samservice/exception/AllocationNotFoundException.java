package com.cognizant.samservice.exception;

public class AllocationNotFoundException extends RuntimeException {

    public AllocationNotFoundException(String message) {
        super(message);
    }

    public AllocationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
