package com.algoExpert.demo.Mapper;

import com.algoExpert.demo.Dto.AssigneeDto;
import com.algoExpert.demo.Entity.Assignee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AssigneeMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Assignee assigneeDtoToAssignee(AssigneeDto assigneeDto){
        return modelMapper.map(assigneeDto,Assignee.class);
    }
    public AssigneeDto assigneeToAssigneeDto(Assignee assignee){
        return modelMapper.map(assignee,AssigneeDto.class);
    }
    public List<AssigneeDto> assigneeDtos(List<Assignee> assignees){
        return assignees.stream()
                .map(assignee -> modelMapper.map(assignee,AssigneeDto.class))
                .collect(Collectors.toList());
    }
}
