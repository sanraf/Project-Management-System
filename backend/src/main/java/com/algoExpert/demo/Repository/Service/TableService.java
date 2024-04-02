package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Dto.ProjectDto;
import com.algoExpert.demo.Dto.TableDto;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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