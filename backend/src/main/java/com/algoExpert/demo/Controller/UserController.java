package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Jwt.JwtResponse;
import com.algoExpert.demo.Jwt.JwtService;
import com.algoExpert.demo.Repository.Service.Impl.RefreshTokenSevice;
import com.algoExpert.demo.Repository.Service.ProjectUserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ProjectUserService projectUserService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenSevice refreshTokenSevice;

    @GetMapping("/getSingleProject/{project_id}")
    public Project getSingleProject(@PathVariable int project_id){
        return projectUserService.findProject(project_id);
    }
  
    @GetMapping("/fetchUserProject")
    public List<Project> getUserProject(){
        return projectUserService.getUserProjectIds();
    }

    @GetMapping("/refreshToken")
    public JwtResponse refreshJwtToken(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        Boolean checkIfExpired = true;
        String refreshTokenRequest = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwtToken")) {
                    checkIfExpired = jwtService.extractExpiration(cookie.getValue()).before(new Date());
                }
                if(cookie.getName().equals("refreshToken")){
                    refreshTokenRequest = cookie.getValue();
                }
            }
        }
        if(checkIfExpired){
            // Proceed with token refresh logic if the token is expired
            return projectUserService.refreshJwtToken(refreshTokenRequest);
        }
        return JwtResponse.builder().jwtToken("jwt token not expired").build();
    }

    @DeleteMapping("/logoutUser")
    public Boolean logoutUser(){
        return refreshTokenSevice.userLogout();
    }
}
