package com.algoExpert.demo.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class CommentDto {

    private int comment_id;
    private String username;
    private String date_created;
    private String commentBody;

    public CommentDto() {
    }

    public CommentDto(int comment_id, String username, String date_created, String commentBody) {
        this.comment_id = comment_id;
        this.username = username;
        this.date_created = date_created;
        this.commentBody = commentBody;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }
}
