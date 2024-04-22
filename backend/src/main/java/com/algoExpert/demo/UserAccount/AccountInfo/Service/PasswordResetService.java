package com.algoExpert.demo.UserAccount.AccountInfo.Service;

import com.algoExpert.demo.Records.PasswordRequest;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.UserAccount.AccountInfo.Entity.PasswordReset;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface  PasswordResetService {

    String confirmPassword(String token) throws InvalidArgument;
    void saveToken(PasswordReset passwordReset);
    PasswordReset createPasswordReset(PasswordRequest passwordRequest)throws InvalidArgument;
    void deleteResetPassword(PasswordReset confirmation);
    boolean isTokenExpired(LocalDateTime expiresAt);
    PasswordReset findPasswordByUserId(int userId);
    PasswordReset findPasswordByToken(String token)throws InvalidArgument;

}
