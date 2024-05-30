package com.algoExpert.demo.ExceptionHandler;

public class JwtTokenMalformedException extends RuntimeException {
    public JwtTokenMalformedException(String message, Throwable cause) {
        super(message, cause);
    }
}



