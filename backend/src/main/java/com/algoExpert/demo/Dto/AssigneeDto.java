package com.algoExpert.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssigneeDto {

    private Integer assignee_id;
    private Integer member_id;
    private Integer task_id;
    private String username;
}
