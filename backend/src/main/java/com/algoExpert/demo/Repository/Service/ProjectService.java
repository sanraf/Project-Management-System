package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ProjectService {

    //  create project
    Integer createProject(Project project) throws InvalidArgument, AccessDeniedException, MessagingException, IOException;

    //  get all projects
    List<Project> getAllProjects();

    //get one project
    @Transactional
    List<Project> deleteProjectById(Integer projectId) throws InvalidArgument;

    Project editProject(Project Project) throws InvalidArgument;
}