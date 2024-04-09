package org.example.errors;

public class LimitAccountException extends Exception {
    public LimitAccountException(String message) {
        super(message);
    }
}
