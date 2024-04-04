package com.algoExpert.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int comment_id;
    private String username;
    private String date_created;
    private String commentBody;
}
