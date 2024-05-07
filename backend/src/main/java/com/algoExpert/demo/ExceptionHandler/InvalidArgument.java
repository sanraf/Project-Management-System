package com.algoExpert.demo.ExceptionHandler;

import org.springframework.http.HttpStatus;

public class InvalidArgument extends RuntimeException{
    public InvalidArgument(String errorMessage){
        super(errorMessage);
    }
}
