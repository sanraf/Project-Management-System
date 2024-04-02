package com.algoExpert.demo.Mapper;

import com.algoExpert.demo.Dto.AssigneeDto;
import com.algoExpert.demo.Dto.CommentDto;
import com.algoExpert.demo.Entity.Assignee;
import com.algoExpert.demo.Entity.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    public static CommentDto mapToCommentDto(Comment comment){
        return new CommentDto(
                comment.getComment_id(),
                comment.getCommentBody()
        );
    }

    public static Comment mapToComment(CommentDto commentDto){
        return new Comment(
                commentDto.getComment_id(),
                commentDto.getCommentBody()
        );
    }
}
