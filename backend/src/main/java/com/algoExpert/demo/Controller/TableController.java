package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.Table;

import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;

//    create table
    @PostMapping("/createTable/{project_id}/{member_id}")
    public Project createTable(@PathVariable Integer project_id, @PathVariable int member_id) throws InvalidArgument{
        return tableService.createTable(project_id,member_id);
    }

//    get all tables
    @GetMapping("/getAllTables")
    public List<Table> getAllTables() {
    return tableService.getAllTables();
}

//    delete table
    @DeleteMapping("/deleteTable/{project_id}/{table_id}")

    public List<Table> deleteTable(@PathVariable Integer project_id, Integer table_id) throws InvalidArgument{
        return tableService.deleteTable(project_id,table_id);
    }

//    update table
    @PutMapping("/updateTable/{table_id}")
    public Table updateTable(@RequestBody Table table,@PathVariable int table_id) throws InvalidArgument {
        return tableService.editTable( table,table_id);
    }


}