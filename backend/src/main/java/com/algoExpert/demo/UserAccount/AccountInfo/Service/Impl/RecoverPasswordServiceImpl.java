package com.algoExpert.demo.UserAccount.AccountInfo.Service.Impl;

import com.algoExpert.demo.Records.PasswordRequest;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.UserRepository;
import com.algoExpert.demo.UserAccount.AccountInfo.Entity.PasswordReset;
import com.algoExpert.demo.UserAccount.AccountInfo.Repository.PasswordResetRepository;
import com.algoExpert.demo.UserAccount.AccountInfo.Service.PasswordResetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.algoExpert.demo.AppUtils.AppConstants.*;

@Service
@Slf4j
public class RecoverPasswordServiceImpl implements PasswordResetService {

    @Autowired
    private PasswordResetRepository resetRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    static final Long EXPIRING_TIME = 2L;
    @Value("${confirm.password.url}")
    String confirmLink;


    @Override
    public String confirmPassword(String token) throws InvalidArgument {
        PasswordReset passwordReset = findPasswordByToken(token);

        if (isTokenExpired(passwordReset.getExpiresAt())){
            deleteResetPassword(passwordReset);
            return "The password reset link has expired. Please request a new one to continue";
        }

        User user = passwordReset.getUser();
        String newPassword = passwordReset.getNewPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        deleteResetPassword(passwordReset);
        String link = "http://localhost:8080/login";
        log.info("Password reset was successful: {}", login(link));
        return "Password reset was successful.: "+login(link);
    }

    @Override
    public void saveToken(PasswordReset passwordReset) {
        resetRepository.save(passwordReset);
    }

    @Override
    public PasswordReset createPasswordReset(PasswordRequest passwordRequest) throws InvalidArgument {
        User user = userRepository.findByEmail(passwordRequest.userName()).orElseThrow(()-> new InvalidArgument(String.format(USERNAME_NOT_FOUND,passwordRequest.userName())));

        if (!passwordRequest.password().trim().equals(passwordRequest.confirmPassword().trim())){
            throw new InvalidArgument(PASSWORD_MISMATCH);
        }
        String token = UUID.randomUUID().toString();

        PasswordReset passwordReset = PasswordReset.builder()
                .passwordToken(token)
                .newPassword(passwordRequest.confirmPassword())
                .expiresAt(LocalDateTime.now().plusMinutes(EXPIRING_TIME))
                .user(user).build();
        saveToken(passwordReset);
        String link = confirmLink + passwordReset.getPasswordToken();
        log.info("Click the link to reset the password : {}", login(link));
        return passwordReset;
    }

    @Override
    public void deleteResetPassword(PasswordReset confirmation) {
        resetRepository.delete(confirmation);
    }

    @Override
    public boolean isTokenExpired(LocalDateTime expiresAt) {
        return expiresAt.isBefore(LocalDateTime.now());
    }

    @Override
    public PasswordReset findPasswordByUserId(int userId) {
        return null;
    }

    @Override
    public PasswordReset findPasswordByToken(String token) throws InvalidArgument {
        return resetRepository.findByPasswordToken(token).orElseThrow(()->new InvalidArgument(String.format(TOKEN_NOT_FOUND,token)));

    }

    String login(String link){
        return "<a href=\"" + link + "\">";
    }

}
