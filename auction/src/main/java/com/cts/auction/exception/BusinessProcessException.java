package com.cts.auction.exception;

public class BusinessProcessException extends RuntimeException{
    public BusinessProcessException(String message) {
        super(message);
    }

    public BusinessProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
