package com.algoExpert.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
