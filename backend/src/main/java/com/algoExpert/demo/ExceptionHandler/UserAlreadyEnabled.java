package com.algoExpert.demo.ExceptionHandler;

public class UserAlreadyEnabled extends RuntimeException{
    public UserAlreadyEnabled(String errorMessage){
        super(errorMessage);
    }
}
