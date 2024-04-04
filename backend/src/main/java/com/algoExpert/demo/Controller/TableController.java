package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Dto.ProjectDto;
import com.algoExpert.demo.Dto.TableDto;
import com.algoExpert.demo.Entity.TaskContainer;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;

    //    create table
    @PostMapping("/createTable/{project_id}/{member_id}")
    public ProjectDto createTable(@PathVariable Integer project_id, @PathVariable int member_id) throws InvalidArgument {
        return tableService.createTable(project_id, member_id);
    }

    //    get all tables
    @GetMapping("/getAllTables")
    public List<TaskContainer> getAllTables() {
        return tableService.getAllTables();
    }

    //    delete table
    @DeleteMapping("/deleteTable/{project_id}/{table_id}")
    public List<TaskContainer> deleteTable(@PathVariable Integer project_id, Integer table_id) throws InvalidArgument {
        return tableService.deleteTable(project_id, table_id);
    }

    //    update table
    @PutMapping("/updateTable")
    public TableDto updateTable(@RequestBody TableDto tableDto) throws InvalidArgument {
        return tableService.editTable(tableDto);
    }
}