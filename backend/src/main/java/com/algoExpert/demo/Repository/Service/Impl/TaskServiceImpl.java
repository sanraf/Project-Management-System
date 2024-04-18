package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.Dto.TaskDto;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.TaskMapper;
import com.algoExpert.demo.Repository.*;
import com.algoExpert.demo.Repository.Service.TaskService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    private  UserRepository userRepository;

    //    create new task
    @Override
    public TaskContainer createTask(int table_id) throws InvalidArgument {

        TaskContainer table = tableRepository.findById(table_id).orElseThrow(() ->
                new InvalidArgument("TaskTable with ID " + table_id + " not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = null;

        if (authentication != null && authentication.isAuthenticated()) {
            // Get the principal (authenticated user)
            loggedUser = (User) authentication.getPrincipal();
        }

        List<Task> taskList = table.getTasks();
        int count = taskList.size() + 1;
        Task task = new Task(0, "task " + count, ""
                ,loggedUser.getUsername(), "", "", "", "", null, null);

        taskList.add(task);
        table.setTasks(taskList);

        return tableRepository.save(table);
    }
    //    get all tasks
    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    //    update task
    @Override
    public Task editTask(Task newTask) throws InvalidArgument {

        Task task = taskRepository.findById(newTask.getTask_id())
                .map(existingTask -> {
                    existingTask.setTitle(newTask.getTitle());
                    existingTask.setDescription(newTask.getDescription());
                    existingTask.setStart_date(newTask.getStart_date());
                    existingTask.setEnd_date(newTask.getEnd_date());
                    existingTask.setStatus(newTask.getStatus());
                    existingTask.setPriority(newTask.getPriority());
                    return taskRepository.save(existingTask);
                }).orElseThrow(() -> new InvalidArgument("Task with ID " + newTask.getTask_id() + " not found"));
        return task;
    }

    //duplicate task
    @Override
    public TaskContainer duplicateTask(Task task, Integer table_id) {
        TaskContainer table = tableRepository.findById(table_id).get();

        Task newTask = new Task(0, task.getTitle(), task.getDescription(), task.getUsername(), task.getStart_date(), task.getEnd_date(), task.getStatus(),
                task.getPriority(), null, null);
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

}

