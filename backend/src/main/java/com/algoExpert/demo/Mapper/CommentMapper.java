package com.algoExpert.demo.Mapper;


import com.algoExpert.demo.Dto.CommentDto;
import com.algoExpert.demo.Entity.Comment;
import org.springframework.stereotype.Component;


@Component
public class CommentMapper {
    public static CommentDto mapToCommentDto(Comment comment){
        return new CommentDto(
                comment.getComment_id(),
                comment.getUsername(),
                comment.getDate_created(),
                comment.getCommentBody()
        );
    }

    public static Comment mapToComment(CommentDto commentDto){
        return new Comment(
                commentDto.getComment_id(),
                commentDto.getUsername(),
                commentDto.getDate_created(),
                commentDto.getCommentBody()

        );
    }
}
