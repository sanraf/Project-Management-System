package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Dto.AssigneeDto;
import com.algoExpert.demo.Entity.Assignee;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.Service.AssigneesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignee")
public class AssigneeController {

    @Autowired
    private AssigneesService assigneesService;

    //    assign member to task using their IDs
    @GetMapping("/saveAssignee/{member_id}/{task_id}")
    private Task saveAssignee(@PathVariable int member_id, @PathVariable int task_id) throws InvalidArgument {
        return assigneesService.assignTaskToMember(member_id, task_id);
    }

//    get all assignees of a task
    @GetMapping("/getAllAssignees")
    public List<Assignee> getAllAssignees() {
        return assigneesService.getAllAssignees();
    }

    @GetMapping("/byTaskId/{taskId}")
    public List<Assignee> getAllAssignees(@PathVariable int taskId) {
        return assigneesService.getAssigneeBrTaskID(taskId);
    }
}
