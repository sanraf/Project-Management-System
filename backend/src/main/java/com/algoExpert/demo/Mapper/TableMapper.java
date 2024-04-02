package com.algoExpert.demo.Mapper;

import com.algoExpert.demo.Dto.TableDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TableMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Table tableDtoToTable(TableDto tableDto){ return modelMapper.map(tableDto,Table.class);}
    public TableDto tableToTableDto(Table table){return modelMapper.map(table,TableDto.class);}

    public List<TableDto> tableDtos(List<Table> tables){
        return tables.stream()
                .map(table -> modelMapper.map(table,TableDto.class))
                .collect(Collectors.toList());
    }
}
