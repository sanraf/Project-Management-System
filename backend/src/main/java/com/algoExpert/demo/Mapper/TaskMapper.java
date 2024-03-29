package com.algoExpert.demo.Mapper;

import com.algoExpert.demo.Dto.TaskDto;
import com.algoExpert.demo.Entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Task taskDtoToTask(TaskDto taskDto){return modelMapper.map(taskDto,Task.class); }

    public TaskDto taskToTaskDto(Task task){return modelMapper.map(task,TaskDto.class);}

    public List<TaskDto> taskDtos(List<Task> tasks){
        return tasks.stream()
                .map(task -> modelMapper.map(task,TaskDto.class))
                .collect(Collectors.toList());
    }
}
