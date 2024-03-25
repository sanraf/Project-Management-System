package com.algoExpert.demo.Service;

import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.Repository.AssigneesRepository;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.TaskRepository;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssigneesService {

	@Autowired
	private AssigneesRepository assigneesRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MemberRepository memberRepository;

//	 Assign a task to member
	public Task assignTaskToMember(int member_id, int task_id){

		Member usernamefromMember=memberRepository.findById(member_id).get();



		Assignee assignee = new Assignee(0, member_id, task_id,usernamefromMember.getUsername());

		Task task = taskRepository.findById(task_id).get();


		List<Assignee> assigneeList=task.getAssignees();
		assigneeList.add(assignee);
		task.setAssignees(assigneeList);

		return taskRepository.save(task);
	}


//  get all projects
	public List<Assignee> getAllAssignees(){return assigneesRepository.findAll(); }
}
