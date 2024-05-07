package com.algoExpert.demo.Records;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record AuthRequest(String email,String password) { }
