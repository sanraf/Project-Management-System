package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Dto.AuthRequest;
import com.algoExpert.demo.Dto.RefreshTokenRequest;
import com.algoExpert.demo.Entity.HttpResponse;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.RefreshToken;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.Jwt.JwtResponse;
import com.algoExpert.demo.Repository.Service.AuthService;
import com.algoExpert.demo.Jwt.JwtService;
import com.algoExpert.demo.Repository.Service.Impl.RefreshTokenSevice;
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
    private JwtService jwtService;


    @Autowired
    private RefreshTokenSevice  tokenSevice;
//    create user
    @PostMapping("/registerUser")
    public User registerUser(@RequestBody User user){
        return authService.register(user);
    }

    @PostMapping("/login")
    public HttpResponse userLogin(@RequestBody User userCredentials){
        return authService.login(userCredentials);
    }


    @PostMapping("/authenticate")
    public JwtResponse authenticateAndGetToken(@ RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = tokenSevice.createRefreshToken(authRequest.getUsername());
            return   JwtResponse.builder()
                    .jwtToken(jwtService.generateToken(authRequest.getUsername()))
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
