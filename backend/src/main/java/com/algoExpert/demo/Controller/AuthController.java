package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Dto.AuthRequest;
import com.algoExpert.demo.Entity.HttpResponse;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.Repository.Service.AuthService;
import com.algoExpert.demo.Jwt.JwtService;
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
//    create user
    @PostMapping("/registerUser")
    public User registerUser(@RequestBody User user){
        return authService.register(user);
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
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
