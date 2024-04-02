package com.algoExpert.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer task_id;
    private String title;
    private String description;
    private String username;
    private String start_date;
    private String end_date;
    private String status;
    private String priority;

//    relationships
   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   List<Comment> comments;

   @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
   List<Assignee> assignees;


}
