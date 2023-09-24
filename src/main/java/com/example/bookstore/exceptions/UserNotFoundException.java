package com.example.bookstore.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
    }
}
