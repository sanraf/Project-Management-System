package com.algoExpert.demo.Repository.Service.Impl;


import com.algoExpert.demo.AppNotification.EmailHtmlLayout;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.TaskMapper;
import com.algoExpert.demo.Repository.*;
import com.algoExpert.demo.Repository.Service.TaskService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.*;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AssigneesRepository assigneesRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private ProjectUserImpl projectUser;

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private EmailHtmlLayout emailHtmlLayout;

    //    create new task
    @Override
    public TaskContainer createTask(int table_id) throws InvalidArgument {

        TaskContainer table = tableRepository.findById(table_id).orElseThrow(() ->
                new InvalidArgument("TaskTable with ID " + table_id + " not found"));

        String projectName = table.getTasks()
                .stream().map(Task::getProjectName).findFirst().orElseThrow();
        String username =  userRepository.findById(projectUser.loggedInUserId()).orElseThrow().getFullName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = null;

        if (authentication != null && authentication.isAuthenticated()) {
            // Get the principal (authenticated user)
            loggedUser = (User) authentication.getPrincipal();
        }

        List<Task> taskList = table.getTasks();
        int count = taskList.size() + 1;
        Task task = new Task(0, "task " + count, ""
                ,username, "", "", "", "",projectName, null, null);

        taskList.add(task);
        table.setTasks(taskList);
        System.err.println(projectName);
        return tableRepository.save(table);
    }
    //    get all tasks
    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    //    update task
    @Override
    public Task editTask(Task newTask){
        return   taskRepository.findById(newTask.getTask_id())
                .map(existingTask ->{
                    Optional.ofNullable(newTask.getTitle()).ifPresent(existingTask::setTitle);
                    Optional.ofNullable(newTask.getDescription()).ifPresent(existingTask::setDescription);
                    Optional.ofNullable(newTask.getStart_date()).ifPresent(existingTask::setStart_date);
                    Optional.ofNullable(newTask.getEnd_date()).ifPresent(existingTask::setEnd_date);
                    Optional.ofNullable(newTask.getStatus()).map(String::toUpperCase).ifPresent(existingTask::setStatus);
                    Optional.ofNullable(newTask.getPriority()).map(String::toUpperCase).ifPresent(existingTask::setPriority);
                    return taskRepository.save(existingTask);
                }).orElseThrow(() -> new IllegalArgumentException("task with ID " + newTask.getTask_id() + " not found"));
    }

    //duplicate task
    @Override
    public TaskContainer duplicateTask(Task task, Integer table_id) {
        TaskContainer table = tableRepository.findById(table_id).get();

        Task newTask = new Task(0, task.getTitle(), task.getDescription(), task.getUsername(), task.getStart_date(), task.getEnd_date(), task.getStatus(),
                task.getPriority(),task.getProjectName(), null, null);
        List<Task> taskList = table.getTasks();


        taskList.add(newTask);
        table.setTasks(taskList);
        return tableRepository.save(table);
    }

    //  delete task
    @Override
    @Transactional
    public TaskContainer deleteTaskById(Integer task_id, Integer table_id) throws InvalidArgument {
        Task storedTask = taskRepository.findById(task_id).orElseThrow(() ->
                new InvalidArgument("Task with ID " + task_id + " not found"));
        TaskContainer table = tableRepository.findById(table_id).orElseThrow(() ->
                new InvalidArgument("TaskTable with ID " + table_id + " not found"));

        List<Comment> comments = storedTask.getComments();
        if (!comments.isEmpty()) {
            for (Comment comment : comments) {
                commentRepository.delete(comment);
            }
        }
        List<Task> taskList = table.getTasks();
        taskList.remove(storedTask);
        table.setTasks(taskList);
        tableRepository.save(table);
        return table;
    }

    @Override
    public Task getTaskById(int taskId) {
        return taskRepository.findById(taskId).orElseThrow();
    }

    @Override
    public List<Task> findTaskByDateAndStatus(String date, String status) {
        return taskRepository.findTasksDueDate(date,status);
    }


}

