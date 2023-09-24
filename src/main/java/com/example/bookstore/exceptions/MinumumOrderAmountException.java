package com.example.bookstore.exceptions;

public class MinumumOrderAmountException extends RuntimeException{
    public MinumumOrderAmountException(String message) {
        super(message);
    }
}
