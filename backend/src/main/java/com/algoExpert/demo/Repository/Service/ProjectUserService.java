package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Jwt.JwtResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectUserService {
    Project findProject(int project_id) throws InvalidArgument;
    Integer loggedInUserId();
    List<Project> getUserProjectIds();
    JwtResponse refreshJwtToken(String RefreshTokenRequest);

}
