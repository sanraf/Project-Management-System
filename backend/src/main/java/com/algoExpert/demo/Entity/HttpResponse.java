package com.algoExpert.demo.Entity;

import com.algoExpert.demo.role.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@SuperBuilder
@JsonInclude(NON_DEFAULT)
public class HttpResponse {
    protected String timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String method;
    protected String urlInstance;
    protected String token;
    protected String refreshToken;
    protected String email;
    protected int userId;
    protected String fullname;
    protected String developerMessage;
    protected List<Role> role;
}
