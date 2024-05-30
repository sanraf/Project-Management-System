package com.algoExpert.demo.ExceptionHandler;

public class JwtTokenExpiredException extends RuntimeException {
    public JwtTokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}