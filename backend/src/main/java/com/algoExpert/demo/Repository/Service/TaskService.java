package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Dto.TaskDto;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {


    //    create new task
    TaskContainer createTask(int member_id, int table_id) throws InvalidArgument;

    //    get all tasks
    List<Task> getAllTask();

    //    update task
    TaskDto editTask(TaskDto newTaskDto) throws InvalidArgument;

    //duplicate task
    TaskContainer duplicateTask(Task task, Integer table_id);

    //  delete task
    @Transactional
    TaskContainer deleteTaskById(Integer taskId, Integer table_id) throws InvalidArgument;

}

