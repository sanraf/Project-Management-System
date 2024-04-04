package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.Repository.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
//    create user
    @PostMapping("/registerUser")
    public User registerUser(@RequestBody User user){
        return authService.register(user);
    }

    @PostMapping("/login")
    public void userLogin(){
        System.out.print("user is logged in the system");
    }

//    get all users of the system
//    @GetMapping("/getAllUsers")
//    public List<User> getAll(){
//        return authService.getUsers();
//    }
//
//    @GetMapping("/fetchUserProject/{user_id}")
//    public List<Project> getUserProject(@PathVariable int user_id){
//        return authService.getUserProjectIds(user_id);
//    }
}
