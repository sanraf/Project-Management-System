package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Dto.AssigneeDto;
import com.algoExpert.demo.Entity.Assignee;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.AssigneeMapper;
import com.algoExpert.demo.Repository.AssigneesRepository;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssigneesService {

    //	 Assign a task to member
    Task assignTaskToMember(int member_id, int task_id) throws InvalidArgument;

    //  get all projects
    List<Assignee> getAllAssignees();

    public List<Assignee> getAssigneeBrTaskID(int taskId);

//	get all assignees

}
