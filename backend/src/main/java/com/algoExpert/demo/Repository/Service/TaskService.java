package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Dto.TaskDto;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.TaskMapper;
import com.algoExpert.demo.Repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskService {


    //    create new task
    TaskTable createTask(int member_id, int table_id) throws InvalidArgument;

    //    get all tasks
    List<Task> getAllTask();

    //    update task
    TaskDto editTask(TaskDto newTaskDto) throws InvalidArgument;

    //duplicate task
    TaskTable duplicateTask(Task task, Integer table_id);

    //  delete task
    @Transactional
    TaskTable deleteTaskById(Integer taskId, Integer table_id) throws InvalidArgument;

}

