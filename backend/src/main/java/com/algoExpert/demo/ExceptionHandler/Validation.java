package com.algoExpert.demo.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Validation {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String,String> handleValidation(ConstraintViolationException argumentException){
        Map<String,String> errorMap = new HashMap<>();
        argumentException.getConstraintViolations().forEach(errorMessage->{
            errorMap.put(errorMessage.getPropertyPath().toString(),errorMessage.getMessage());
        });
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidArgument.class)
    public Map<String,String> handleInvalidArgument(InvalidArgument argument){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", argument.getMessage());
        return errorMap;
    }
}
