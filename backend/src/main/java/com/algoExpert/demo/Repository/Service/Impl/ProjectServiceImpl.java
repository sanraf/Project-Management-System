package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.ProjectMapper;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.Service.*;
import com.algoExpert.demo.Repository.TableRepository;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TableService tableService;

    @Autowired
    private MemberService memberService;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ProjectUserImpl projectUser;

    //  create project
    @Override
    public Integer createProject(Project project) throws InvalidArgument {
        // Find user by id
        User user = userRepository.findById(projectUser.loggedInUserId())
                .orElseThrow(() -> new InvalidArgument("User with ID " + projectUser.loggedInUserId() + " not found"));
        project.setUser(user);

        // Save the project and retrieve the saved instance
        Project savedProject = projectRepository.save(project);
        // save member
        Member newMember = memberService.inviteMember(savedProject.getProject_id(),user.getUser_id());

        // Create a default table using new member id
        tableService.createTable(savedProject.getProject_id());

        // Save the updated project with the added member
        savedProject = projectRepository.save(savedProject);

        return savedProject.getProject_id();
    }

    //  get all projects
    @Override
    public List<Project> getAllProjects() {
      return projectRepository.findAll();
    }
    //get one project

    //    delete project
    @Override
    public List<Project> deleteProjectById(Integer projectId) throws InvalidArgument {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new InvalidArgument("Project with ID " + projectId + " not found"));

        // Delete associated tables
        for (TaskContainer table : project.getTables()) {
            tableRepository.delete(table);
        }

        // Delete associated members
        for (Member member : project.getMemberList()) {
            memberRepository.delete(member);
        }
        // Now delete the project
        projectRepository.delete(project);

        return projectRepository.findAll();
    }

    //    update project
    @Override

    public Project editProject(Project newProjectValue) throws InvalidArgument{
        return projectRepository.findById(newProjectValue.getProject_id())
                .map(existingProject -> {
                    if (newProjectValue != null) {
                        Optional.ofNullable(newProjectValue.getTitle()).ifPresent(existingProject::setTitle);
                        Optional.ofNullable(newProjectValue.getDescription()).ifPresent(existingProject::setDescription);
                    }
                    return projectRepository.save(existingProject);
                }).orElseThrow(() -> new InvalidArgument("Task with ID " + newProjectValue.getProject_id() + " not found"));

    }

}