package com.algoExpert.demo.Mapper;

import com.algoExpert.demo.Dto.TableDto;
import com.algoExpert.demo.Entity.TaskTable;

public class TableMapper {
    public static TableDto mapToTableDto(TaskTable taskTable) {
        return new TableDto(
                taskTable.getTable_id(),
                taskTable.getTable_name(),
                taskTable.getTasks()
        );
    }

    public static TaskTable mapToTaskTable(TableDto taskTableDto) {
        return new TaskTable(
                taskTableDto.getTable_id(),
                taskTableDto.getTable_name(),
                taskTableDto.getTasks()
        );
    }
}
