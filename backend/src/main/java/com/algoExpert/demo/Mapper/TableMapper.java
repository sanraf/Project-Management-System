package com.algoExpert.demo.Mapper;

import com.algoExpert.demo.Dto.TableDto;
import com.algoExpert.demo.Entity.TaskContainer;
import org.springframework.stereotype.Component;

@Component
public class TableMapper {
    public static TableDto mapToTableDto(TaskContainer taskContainer) {
        return new TableDto(
                taskContainer.getTable_id(),
                taskContainer.getTable_name(),
                taskContainer.getTasks()
        );
    }

    public static TaskContainer mapToTaskTable(TableDto taskTableDto) {
        return new TaskContainer(
                taskTableDto.getTable_id(),
                taskTableDto.getTable_name(),
                taskTableDto.getTasks()
        );
    }
}
