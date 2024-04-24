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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
    public User registerUser(@RequestBody RegistrationRequest request) throws InvalidArgument, InvalidArgument {
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    public HttpResponse userLogin(@RequestBody User userCredentials){
        return authService.login(userCredentials);
    }


    //    get all users of the system
//    @GetMapping("/getAllUsers")
//    public List<User> getAll(){
//        return authService.getUsers();
//    }
//
    @PostMapping("/authenticate")
    public JwtResponse authenticateAndGetToken(@ RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = tokenSevice.createRefreshToken(authRequest.username());
            return   JwtResponse.builder()
                    .jwtToken(jwtService.generateToken(authRequest.username()))
                    .refreshToken(refreshToken.getToken()).build();
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

//    refresh token method
    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){

        return  tokenSevice.findByToken(refreshTokenRequest.getToken())
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.generateToken(user.getUsername());
                    return  JwtResponse.builder()
                            .jwtToken(accessToken)
                            .refreshToken(refreshTokenRequest.getToken()).build();
                }).orElseThrow(() ->new RuntimeException(
                        "Refresh token is not in database"
                ));

    }

}
