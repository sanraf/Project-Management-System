package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Dto.ProjectDto;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.Service.Impl.ProjectUserImpl;
import com.algoExpert.demo.Repository.Service.ProjectService;
import com.algoExpert.demo.Repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserRepository userRepository;

//  create project using user id
    @PostMapping("/createProject")
    public Integer createProject(@RequestBody Project project) throws InvalidArgument, MessagingException, IOException {
        return projectService.createProject(project);
    }

//    get all projects
    @GetMapping("/findAllProject")
    public List<Project> getAllProject(){
        return projectService.getAllProjects();
    }


    @DeleteMapping("/deleteProject/{project_id}")
    public List<Project> deleteProject(@PathVariable int project_id) throws InvalidArgument{
        return projectService.deleteProjectById(project_id);
    }

    @PutMapping("/editProject")
    public Project deleteProject(@RequestBody Project project) throws InvalidArgument{
        return projectService.editProject(project);
    }

    @GetMapping("/fetchUserProject/{user_id}")
    public User d(@PathVariable int user_id) throws InvalidArgument {
        return userRepository.findById(user_id).orElseThrow();
    }



}
