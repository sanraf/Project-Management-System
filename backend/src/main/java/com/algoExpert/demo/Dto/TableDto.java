package com.algoExpert.demo.Dto;

import com.algoExpert.demo.Entity.Task;

import java.util.List;

public class TableDto {
    private int table_id;
    private String table_name;
    List<Task> tasks;

    public TableDto() {
    }

    public TableDto(int table_id, String table_name, List<Task> tasks) {
        this.table_id = table_id;
        this.table_name = table_name;
        this.tasks = tasks;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
