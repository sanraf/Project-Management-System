package com.algoExpert.demo.Records;

import lombok.AllArgsConstructor;
import lombok.Data;


public record RegistrationRequest(String fullName,String email,String password) {
}
