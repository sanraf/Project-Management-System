package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.Service.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ProjectUserService projectUserService;
    @GetMapping("/getSingleProject/{project_id}")
    public User getSingleProject(@PathVariable int project_id) throws InvalidArgument {
        return projectUserService.findProject(project_id);
    }
    @GetMapping("/fetchUserProject")
    public List<Project> getUserProject(){
        return projectUserService.getUserProjectIds();
    }

}
