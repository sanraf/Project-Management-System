package com.algoExpert.demo.Service;

import com.algoExpert.demo.Dto.ProjectDto;
import com.algoExpert.demo.Dto.TableDto;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.Table;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.ProjectMapper;
import com.algoExpert.demo.Mapper.TableMapper;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.TableRepository;
import com.algoExpert.demo.Repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface TableService {

    //  create a new table
    public ProjectDto createTable(int project_id, int member_id) throws InvalidArgument;

    //  get all tables
    public List<TableDto> getAllTables();

    // update table
    public TableDto editTable(TableDto newTableValue, int table_id) throws InvalidArgument;

    // delete table
    @Transactional
    List<TableDto> deleteTable(Integer project_id, Integer table_id) throws InvalidArgument;
}