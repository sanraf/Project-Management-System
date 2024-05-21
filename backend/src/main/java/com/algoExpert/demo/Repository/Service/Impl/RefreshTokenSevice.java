package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.Entity.RefreshToken;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.Repository.RefreshTokenRepository;
import com.algoExpert.demo.Repository.Service.RefreshTokenInt;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenSevice implements RefreshTokenInt {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

//    method to create refresh token
    public RefreshToken createRefreshToken(String username){
        User user = userRepository.findByEmail(username).get();
        Optional <RefreshToken> refreshTokenUser = refreshTokenRepository.findTokenByUserId(user.getUser_id());
        Boolean recordDeleted = false;

        if(refreshTokenUser.isPresent() && refreshTokenUser.get().getExpiryDate().isBefore(Instant.now())){
                refreshTokenRepository.delete(refreshTokenUser.get());
                recordDeleted = true;
        }

        if(refreshTokenUser.isEmpty() || recordDeleted){
            RefreshToken refreshToken =  RefreshToken.builder()
                    .user(user)
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusMillis(600000))
                    .build();
            return refreshTokenRepository.save(refreshToken);
        }
        return refreshTokenUser.get();
    }

    public RefreshToken createRefreshToken2(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        Optional<RefreshToken> existingRefreshToken = refreshTokenRepository.findTokenByUserId(user.getUser_id());
        boolean shouldCreateNewToken = false;

        if (existingRefreshToken.isPresent()) {
            RefreshToken refreshToken = existingRefreshToken.get();
            if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
                refreshTokenRepository.delete(refreshToken);
                shouldCreateNewToken = true;
            }
        } else {
            shouldCreateNewToken = true;
        }

        if (shouldCreateNewToken) {
            RefreshToken newRefreshToken = RefreshToken.builder()
                    .user(user)
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusMillis(600000)) // 10 minutes expiry time
                    .build();
            return refreshTokenRepository.save(newRefreshToken);
        } else {
            return existingRefreshToken.get();
        }
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
           refreshTokenRepository.delete(token);
            throw new RuntimeException((token.getToken()+"Refresh token has expired . Please make a new login request"));
        }
        return token;
    }

    @Override
    public Boolean userLogout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the principal (authenticated user)
            User loggedUser = (User) authentication.getPrincipal();
            Optional<RefreshToken> refreshTokenUser = refreshTokenRepository.findTokenByUserId(loggedUser.getUser_id());

            if(refreshTokenUser.isPresent()){
                refreshTokenRepository.delete(refreshTokenUser.get());
                return true;
            }
        }
        return false;
    }
}
