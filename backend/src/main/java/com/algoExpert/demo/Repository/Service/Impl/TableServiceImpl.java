package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.Dto.ProjectDto;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.ProjectMapper;
import com.algoExpert.demo.Mapper.TableMapper;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.Service.TableService;
import com.algoExpert.demo.Repository.TableRepository;
import com.algoExpert.demo.Repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TableMapper tableMapper;
    @Autowired
    ProjectMapper projectMapper;

    //  create a new table
    @Override
    public ProjectDto createTable(int project_id) throws InvalidArgument {
        Project project = projectRepository.findById(project_id).orElseThrow(() -> new InvalidArgument("Project with ID " + project_id + " not found"));

        List<TaskContainer> tables = project.getTables();
//        int count = project.getTables().size();
        TaskContainer table = new TaskContainer(0, "Table", null);
        Task task = new Task(0, "task", "description",project.getUser().getFullName(), "","", "", "", project.getTitle(), null,null);


        tables.add(table);
        project.setTables(tables);
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        table.setTasks(taskList);

        Project projectResult = projectRepository.save(project);
        return ProjectMapper.mapToProjectDto(projectResult);
    }


    //  get all table
    @Override
    public List<TaskContainer> getAllTables() {
        return tableRepository.findAll();
    }

    //  update table
    @Override
    public TaskContainer editTable(TaskContainer newTableValue) throws InvalidArgument {
        if (newTableValue == null) {
            throw new IllegalArgumentException("Invalid newTableValue");
        }

        TaskContainer table = tableRepository.findById(newTableValue.getTable_id())
                .orElseThrow(() -> new InvalidArgument("Task with ID " + newTableValue.getTable_id() + " not found"));

        if (newTableValue.getTable_name() != null) {
            table.setTable_name(newTableValue.getTable_name());
        }

        return tableRepository.save(table);
    }

    //    delete table
    @Override
    @Transactional
    public List<TaskContainer> deleteTable(Integer project_id, Integer table_id) throws InvalidArgument {
        Project project = projectRepository.findById(project_id).orElseThrow(() -> new InvalidArgument("Project with ID " + project_id + " not found"));
        TaskContainer table = tableRepository.findById(table_id).orElseThrow(() -> new InvalidArgument("Table with ID " + table_id + " not found"));

        List<TaskContainer> tablesList = project.getTables();
        tablesList.remove(table);
        project.setTables(tablesList);
        projectRepository.save(project);
        return tableRepository.findAll();
    }

}

