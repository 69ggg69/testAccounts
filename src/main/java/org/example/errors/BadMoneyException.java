package org.example.errors;

public class BadMoneyException extends Exception{
    public BadMoneyException(String message) {
        super(message);
    }
}
