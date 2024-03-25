package com.algoExpert.demo.ExceptionHandler;

public class InvalidArgument extends Exception{


    public InvalidArgument(String errorMessage){
        super(errorMessage);
    }
}
