package com.algoExpert.demo.ExceptionHandler;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message){
        super(message);
    }
}
