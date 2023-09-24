package com.example.bookstore.exceptions;

public class UnauthorizedException extends RuntimeException{
    public  UnauthorizedException(String message) {
        super(message);
    }
}
