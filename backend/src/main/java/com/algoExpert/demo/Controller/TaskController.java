package com.algoExpert.demo.Controller;

import com.algoExpert.demo.AppNotification.DeadlineTaskReminder;
import com.algoExpert.demo.Entity.Task;

import com.algoExpert.demo.Entity.TaskContainer;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.Service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private DeadlineTaskReminder taskReminder;

    //  create task using table and member id
    @PostMapping("/createTask/{table_id}")
    public TaskContainer createTask( @PathVariable int table_id) throws InvalidArgument {
        return taskService.createTask(table_id);
    }

    //    get all tasks
    @GetMapping("/getAllTask")
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }

    @PutMapping("/editTask")
    public Task editTask(@RequestBody Task taskDto) throws InvalidArgument {
        return taskService.editTask(taskDto);
    }

    // duplicate task by id
    @PostMapping("/duplicateTask/{table_id}")
    public TaskContainer taskUpdate(@PathVariable Integer table_id, @RequestBody Task task) {
        return taskService.duplicateTask(task, table_id);
    }

    //delete task
    @DeleteMapping("/deleteTaskById/{task_id}/{table_id}")
    public TaskContainer deleteTaskById(@PathVariable Integer task_id, @PathVariable Integer table_id) throws InvalidArgument {
        return taskService.deleteTaskById(task_id, table_id);
    }

    @GetMapping("/taskInvitation")
    public Task getAllTaskById(@RequestParam("taskId") int taskId) {
        return taskService.getTaskById(taskId);
    }

    @GetMapping("/taskDueDate")
    public Task taskDueDate(@RequestParam("taskId") int taskId) {
        return taskService.getTaskById(taskId);
    }

}
