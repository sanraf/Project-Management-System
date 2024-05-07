package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Dto.ProjectDto;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.ProjectMapper;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.TableRepository;
import com.algoExpert.demo.Repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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