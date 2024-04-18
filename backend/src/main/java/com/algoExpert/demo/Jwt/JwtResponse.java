package com.algoExpert.demo.Jwt;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {

    private String jwtToken;
    private String refreshToken;

}
