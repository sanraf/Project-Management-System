package com.algoExpert.demo.Dto;

import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.Entity.TaskContainer;
import com.algoExpert.demo.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    private Integer project_id;
    private String title;
    private String description;
    private List<TaskContainer> tables;
    private List<Member> memberList;
    private User user;

}
