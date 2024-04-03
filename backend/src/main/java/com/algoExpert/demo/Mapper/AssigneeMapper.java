package com.algoExpert.demo.Mapper;

import com.algoExpert.demo.Dto.AssigneeDto;
import com.algoExpert.demo.Entity.Assignee;
import org.springframework.stereotype.Component;

@Component
public class AssigneeMapper {

    public static AssigneeDto mapToAssigneeDto(Assignee assignee){
        return new AssigneeDto(
                assignee.getAssignee_id(),
                assignee.getMember_id(),
                assignee.getTask_id(),
                assignee.getUsername()
        );
    }

    public static Assignee mapToAssignee(AssigneeDto assigneeDto){
        return new Assignee(
                assigneeDto.getAssignee_id(),
                assigneeDto.getMember_id(),
                assigneeDto.getTask_id(),
                assigneeDto.getUsername()
        );
    }
}
