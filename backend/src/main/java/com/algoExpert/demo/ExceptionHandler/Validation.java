package com.algoExpert.demo.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLIntegrityConstraintViolationException;
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidArgument.class)
    public Map<String,String> handleInvalidArgument(InvalidArgument argument){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", argument.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.LOCKED)
    @ExceptionHandler(DisabledException.class)
    public Map<String,String> disabledException(DisabledException disabledException){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", disabledException.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ExceptionHandler(SocketException.class)
    public Map<String,String> socketException(SocketException socketException){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", socketException.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public Map<String,String> deniedException(AccessDeniedException deniedException){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", deniedException.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String,String> deniedException(BadCredentialsException badCredentialsException){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", badCredentialsException.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ExpiredJwtException.class)
    public Map<String,String> jwtException(ExpiredJwtException jwtException){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", jwtException.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ExceptionHandler(MailSendException.class)
    public Map<String,String> mailSendException(MailSendException mailSendException){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", mailSendException.getMessage());
        return errorMap;
    }
}
