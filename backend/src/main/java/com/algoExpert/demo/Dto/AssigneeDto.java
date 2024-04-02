package com.algoExpert.demo.Dto;

public class AssigneeDto {

    private Integer assignee_id;
    private Integer member_id;
    private Integer task_id;

    public AssigneeDto() {
    }

    public AssigneeDto(Integer assignee_id, Integer member_id, Integer task_id) {
        this.assignee_id = assignee_id;
        this.member_id = member_id;
        this.task_id = task_id;
    }

    public Integer getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(Integer assignee_id) {
        this.assignee_id = assignee_id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }
}
