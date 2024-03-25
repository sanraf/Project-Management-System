package com.algoExpert.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;
    private String username;
    private String date_created;

    @Column(length = 1000)
    @NotBlank(message = "comment is required")
    private String commentBody;


//    constructors

    public Comment() {
    }

    public Comment(int comment_id, String username, String date_created, String comment) {
        this.comment_id = comment_id;
        this.username = username;
        this.date_created = date_created;
        this.commentBody = comment;
    }

    public int getComment_id() {
        return this.comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate_created() {
        return this.date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getCommentBody() {
        return this.commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }


}
