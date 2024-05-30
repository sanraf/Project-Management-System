package com.algoExpert.demo.UserAccount.AccountInfo.Service.Impl;

import com.algoExpert.demo.Entity.HttpResponse;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.EmptyFieldNotRequired;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Records.ChangePasswordRequest;
import com.algoExpert.demo.Repository.Service.ProjectUserService;
import com.algoExpert.demo.Repository.Service.UserNotificationService;
import com.algoExpert.demo.Repository.UserRepository;
import com.algoExpert.demo.UserAccount.AccountInfo.Service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

import static com.algoExpert.demo.AppUtils.AppConstants.USER_NOT_FOUND;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserNotificationService notificationService;
    @Autowired
    private ProjectUserService projectUserService ;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public HttpResponse createNewPassword(ChangePasswordRequest passwordRequest) {
        int userId = projectUserService.loggedInUserId();
        User user = userRepository.findById(userId).orElseThrow(()-> new InvalidArgument(String.format(USER_NOT_FOUND,userId)));

        boolean isCurrentMatch = passwordEncoder.matches(passwordRequest.currentPassword(),user.getPassword());
        boolean isNewMatch = passwordRequest.newPassword().equals(passwordRequest.confirmNewPassword());

        if (passwordRequest.currentPassword().isBlank() ||
                passwordRequest.newPassword().isBlank() ||
                passwordRequest.confirmNewPassword().isBlank()){
                throw new EmptyFieldNotRequired("All fields are required");
        }

        if (!isCurrentMatch){
            throw new BadCredentialsException("Old password do not match");
        }
        if (!isNewMatch){
            throw new BadCredentialsException("Confirm new password do not match");
        }
        user.setPassword(passwordEncoder.encode(passwordRequest.newPassword()));
        notificationService.createNotification(user,"Your password has been change successful");
        return HttpResponse.builder()
                .timeStamp(LocalTime.now().toString())
                .status(HttpStatus.OK)
                .developerMessage("success")
                .message("Password saved successful")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
