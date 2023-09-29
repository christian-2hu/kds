package com.kdsAPI.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Not found.");
    }
    public NotFoundException(String msg) {
        super(msg);
    }
}