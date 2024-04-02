package com.algoExpert.demo.Dto;

import com.algoExpert.demo.Entity.Comment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
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
    private int owner;
    private String start_date;
    private String end_date;
    private String status;
    private String priority;
    List<Comment> comments;

}
