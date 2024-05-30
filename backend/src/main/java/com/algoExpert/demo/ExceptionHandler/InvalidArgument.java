package com.algoExpert.demo.ExceptionHandler;

public class InvalidArgument extends RuntimeException{
    public InvalidArgument(String errorMessage){
        super(errorMessage);
    }
}
