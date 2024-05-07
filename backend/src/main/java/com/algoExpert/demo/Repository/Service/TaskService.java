package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Service
public interface TaskService {



    //    create new task
    TaskContainer createTask(int table_id) throws InvalidArgument;

    //    get all tasks
    List<Task> getAllTask();

    //    update task
    Task editTask(Task newTask) throws InvalidArgument;

    //duplicate task
    TaskContainer duplicateTask(Task task, Integer table_id);

    //  delete task
    @Transactional
    TaskContainer deleteTaskById(Integer taskId, Integer table_id) throws InvalidArgument;
    Task getTaskById(int taskId);

    List<Task> findTaskByDateAndStatus(String date,String status);

}

