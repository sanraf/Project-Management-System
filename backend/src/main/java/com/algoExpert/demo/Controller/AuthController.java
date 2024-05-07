package com.algoExpert.demo.Controller;

import com.algoExpert.demo.AppNotification.DeadlineTaskReminder;
import com.algoExpert.demo.Dto.RefreshTokenRequest;
import com.algoExpert.demo.Entity.HttpResponse;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.RefreshToken;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Jwt.JwtResponse;
import com.algoExpert.demo.Records.AuthRequest;
import com.algoExpert.demo.Records.RegistrationRequest;
import com.algoExpert.demo.Repository.Service.AuthService;
import com.algoExpert.demo.Jwt.JwtService;
import com.algoExpert.demo.Repository.Service.Impl.RefreshTokenSevice;
import com.algoExpert.demo.Repository.Service.TaskService;
import com.algoExpert.demo.Repository.Service.UserNotificationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DeadlineTaskReminder taskReminder;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserNotificationService userNotificationService;


    @Autowired
    private JwtService jwtService;


    @Autowired
    private RefreshTokenSevice tokenSevice;
//    create user
    @PostMapping("/registerUser")
    public User registerUser(@RequestBody RegistrationRequest request) throws  InvalidArgument, MessagingException, IOException {
        return authService.registerUser(request);
    }

    @PostMapping("/loginUser")
    public HttpResponse userLogin(@RequestBody AuthRequest userCredentials){
        return authService.loginUser(userCredentials);
    }

//    refresh token method

    @GetMapping("/redirect")
    public ModelAndView redirectToLoginPage() {
        String s = "heeloo";
        ModelAndView modelAndView = new ModelAndView("redirect");
        modelAndView.addObject("m",s);
        return modelAndView;

    }

}
