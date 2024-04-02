package com.algoExpert.demo.Dto;

import com.algoExpert.demo.Entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDto {
    private int table_id;
    private String table_name;
    private List<Task> tasks;
}
