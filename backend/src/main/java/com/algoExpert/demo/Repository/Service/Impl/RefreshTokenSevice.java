package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.Entity.RefreshToken;
import com.algoExpert.demo.Repository.RefreshTokenRepository;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenSevice {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

//    method to create refresh token
    public RefreshToken createRefreshToken(String username){
        RefreshToken refreshToken =  RefreshToken.builder()
               .user(userRepository.findByEmail(username).get())
               .token(UUID.randomUUID().toString())
               .expiryDate(Instant.now().plusMillis(600000))
               .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken>  findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }



    private RefreshToken  verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
           refreshTokenRepository.delete(token);
            throw new RuntimeException((token.getToken()+"Refresh token has expired . Please make a new login request"));
        }
        return token;
    }

}
