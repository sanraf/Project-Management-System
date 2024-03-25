package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Entity.Table;
import com.algoExpert.demo.Entity.Task;

import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

//  create task using table and member id
    @PostMapping("/createTask/{member_id}/{table_id}")

    public Table createTask(@PathVariable int member_id,@PathVariable int table_id) throws InvalidArgument {
        return taskService.createTask(member_id,table_id);
    }

//    get all tasks
    @GetMapping("/getAllTask")
    public List<Task> getAllTask(){
        return taskService.getAllTask();
    }

    //delete task
    @DeleteMapping("/deleteTaskById/{task_id}/{table_id}")
    public Table deleteTaskById(@PathVariable Integer task_id, @PathVariable Integer table_id) throws InvalidArgument{
       return  taskService.deleteTaskById(task_id,table_id);
    }

    @PutMapping("/editTask/{task_id}")
    public Task editTask(@RequestBody Task task, @PathVariable Integer task_id) throws InvalidArgument{
        return  taskService.editTask(task,task_id);
    }

    @PostMapping("/duplicateTask/{table_id}")
    public Table taskUpdate(@PathVariable Integer table_id,@RequestBody Task task){
        return taskService.duplicateTask(task,table_id);
    }





//    @PostMapping("/duplicateTask/{task_id}/{table_id}")
//    public Task duplicateTask(@PathVariable int task_id,@PathVariable int table_id){
//        return taskService.duplicateTask(task_id,table_id);
//    }


}
