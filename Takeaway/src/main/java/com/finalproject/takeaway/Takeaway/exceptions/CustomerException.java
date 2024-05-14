package com.finalproject.takeaway.Takeaway.exceptions;

public class CustomerException extends RuntimeException {
    private String details;

    // Constructor that accepts only message
    public CustomerException(String message) {
        super(message);
    }

    // Constructor that accepts both message and cause
    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that accepts message, cause and additional details
    public CustomerException(String message, Throwable cause, String details) {
        super(message, cause);
        this.details = details;
    }

    // Constructor that accepts message and additional details
    public CustomerException(String message, String details) {
        super(message);
        this.details = details;
    }

    // Overriding the toString method to include details
    @Override
    public String toString() {
        return super.toString() + ((details != null) ? " Details: " + details : "");
    }

    // Overriding the getMessage method to include details
    @Override
    public String getMessage() {
        return super.getMessage() + ((details != null) ? " Details: " + details : "");
    }
}
