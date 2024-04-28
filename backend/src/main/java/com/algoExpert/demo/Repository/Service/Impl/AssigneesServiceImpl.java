package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.AppNotification.AppEmailBuilder;
import com.algoExpert.demo.AppNotification.EmailHtmlLayout;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.*;
import com.algoExpert.demo.Repository.Service.AssigneesService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.algoExpert.demo.AppUtils.AppConstants.*;

@Service
@Slf4j
public class AssigneesServiceImpl implements AssigneesService {

    @Autowired
    private AssigneesRepository assigneesRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectUserImpl projectUser;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private AppEmailBuilder appEmailBuilder;
    @Autowired
    private EmailHtmlLayout emailHtmlLayout;
    @Value("${task.invite.url}")
    String taskInviteLink;

    //	 Assign a task to member
    @Transactional
    @Override
    public Task assignTaskToMember(int member_id, int task_id) throws InvalidArgument {
        // check if member and task exist
        Task storedTask = taskRepository.findById(task_id).orElseThrow(() ->
                new InvalidArgument(String.format(TASK_NOT_FOUND,task_id)));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = null;

        if (authentication != null && authentication.isAuthenticated()) {
            // Get the principal (authenticated user)
            loggedUser = (User) authentication.getPrincipal();
        }


        String userName = memberRepository.findById(member_id)
                .orElseThrow(()->new InvalidArgument(String.format(MEMBER_NOT_FOUND,member_id)))
                .getUsername();

        Assignee assignee = new Assignee(0, member_id, task_id, userName);

        List<Assignee> assigneeList= storedTask.getAssignees();
        boolean assigneeExist = assigneeList
                .stream().map(Assignee::getMember_id)
                .anyMatch(id->id.equals(member_id));

        if (assigneeExist){
            throw new InvalidArgument(String.format(ALREADY_ASSIGNED,member_id));
        }

        assigneeList.add(assignee);
        storedTask.setAssignees(assigneeList);
        Task saved = taskRepository.save(storedTask);
        sendInvite(storedTask,loggedUser,storedTask.getProjectName());
        return saved ;
    }


    //	get all assignees
    @Override
    public List<Assignee> getAllAssignees() {
       return assigneesRepository.findAll();

    }

    private void sendInvite(Task task, User user, String projectName){
        String link = taskInviteLink +task.getTask_id();
        appEmailBuilder.sendEmailInvite(TEMP_USER_EMAIL,emailHtmlLayout.assignTaskHtml(user.getFullName(),task.getTitle(),projectName,link));
        log.info(user.getFullName()+" {} :",link);

    }
    @Override
    public List<Assignee> getAssigneeBrTaskID(int taskId){
        return assigneesRepository.findByTaskId(taskId);
    }

}

