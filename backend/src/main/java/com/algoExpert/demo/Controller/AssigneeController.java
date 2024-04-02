package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Dto.AssigneeDto;
import com.algoExpert.demo.Entity.Assignee;
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
    @PostMapping("/saveAssignee/{dtoMember_id}/{dtoTask_id}")
    private AssigneeDto saveAssignee(@PathVariable int dtoMember_id, @PathVariable int dtoTask_id) throws InvalidArgument {
        return assigneesService.assignTaskToMember(dtoMember_id, dtoTask_id);
    }

    @GetMapping("/getAllAssignees")
    public List<Assignee> getAllAssignees() {
        return assigneesService.getAllAssignees();
    }
}
