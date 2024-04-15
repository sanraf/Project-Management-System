package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.Dto.AssigneeDto;
import com.algoExpert.demo.Entity.Assignee;
import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.AssigneesRepository;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.Service.AssigneesService;
import com.algoExpert.demo.Repository.TaskRepository;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssigneesServiceImpl implements AssigneesService {

    @Autowired
    private AssigneesRepository assigneesRepository;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectUserImpl projectUser;

    @Autowired
    private  UserRepository userRepository;

    //	 Assign a task to member
    @Override
    public Task  assignTaskToMember(int member_id, int task_id) throws InvalidArgument {
        // check if member and task exist
        Task storedTask = taskRepository.findById(task_id).orElseThrow(() ->
                new InvalidArgument("Task with ID " + task_id + " not found"));

        User user = userRepository.findById(projectUser.loggedInUserId()).get();

        Assignee assignee = new Assignee(0, member_id, task_id, user.getUsername());

        List<Assignee> assigneeList= storedTask.getAssignees();
        assigneeList.add(assignee);
        storedTask.setAssignees(assigneeList);
        return taskRepository.save(storedTask);
    }
    /*public AssigneeDto assignTaskToMember(int dtoMember_id, int dtoTask_id){
		AssigneeDto assigneeDto = new AssigneeDto(0,dtoMember_id,dtoTask_id);
		Assignee assignee = new Assignee(assigneeDto.getAssignee_id(), assigneeDto.getMember_id(), assigneeDto.getTask_id());
		Assignee assigneeResult = assigneesRepository.save(assignee);
		return assigneeMapper.assigneeToAssigneeDto(assigneeResult);
	}*/

    //	get all assignees
    @Override
    public List<Assignee> getAllAssignees() {
       return assigneesRepository.findAll();

    }

}

