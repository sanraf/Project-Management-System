package com.algoExpert.demo.Dto;

import com.algoExpert.demo.Entity.Assignee;
import com.algoExpert.demo.Entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Integer task_id;
    private String title;
    private String description;
    private String username;
    private String start_date;
    private String end_date;
    private String status;
    private String priority;
    private String projectName;

    private List<Comment> comments;
    private List<Assignee> assignees;
}
