package com.example.bookstore.exceptions;

public class InvalidPasswordException extends Throwable {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
