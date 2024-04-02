package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Dto.ProjectDto;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.ProjectMapper;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.TableRepository;
import com.algoExpert.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectService {

    //  create project
    public Integer createProject(Project project, int user_id) throws InvalidArgument;

    //  get all projects
    public List<ProjectDto> getAllProjects();

    //get one project
    public ProjectDto findProject(int project_id) throws InvalidArgument;

    @Transactional
    public List<Project> deleteProjectById(Integer projectId) throws InvalidArgument;

    public ProjectDto editProject(ProjectDto projectDto, int project_id) throws InvalidArgument;

}