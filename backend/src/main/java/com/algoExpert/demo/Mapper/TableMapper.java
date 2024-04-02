package com.algoExpert.demo.Mapper;

import com.algoExpert.demo.Dto.TableDto;
import com.algoExpert.demo.Entity.TaskTable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TableMapper {
    @Autowired
    private ModelMapper modelMapper;

    public TaskTable tableDtoToTable(TableDto tableDto){ return modelMapper.map(tableDto,TaskTable.class);}
    public TableDto tableToTableDto(TaskTable table){return modelMapper.map(table,TableDto.class);}

    public List<TableDto> tableDtos(List<TaskTable> tables){
        return tables.stream()
                .map(table -> modelMapper.map(table,TableDto.class))
                .collect(Collectors.toList());
    }
}
