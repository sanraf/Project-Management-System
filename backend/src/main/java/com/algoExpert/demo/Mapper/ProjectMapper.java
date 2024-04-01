package com.algoExpert.demo.Mapper;

import com.algoExpert.demo.Dto.ProjectDto;
import com.algoExpert.demo.Entity.Project;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Project projectDtoToProject(ProjectDto projectDto){return modelMapper.map(projectDto,Project.class);}
    public ProjectDto projectToProjectDto(Project project){return modelMapper.map(project,ProjectDto.class);}

    public List<ProjectDto> projectDtos(List<Project> projects){
        return projects.stream().map(project -> modelMapper.map(project,ProjectDto.class)).collect(Collectors.toList());
    }

}
