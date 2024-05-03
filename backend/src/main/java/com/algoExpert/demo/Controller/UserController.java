package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.Service.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ProjectUserService projectUserService;
    @GetMapping("/getSingleProject")
    public ResponseEntity<String> getSingleProject(@RequestParam("project_id") int project_id) throws InvalidArgument {
        projectUserService.findProject(project_id);
        return ResponseEntity.ok("http://localhost:5173/");
    }
    @GetMapping("/fetchUserProject")
    public List<Project> getUserProject(){
        return projectUserService.getUserProjectIds();
    }

}
