package com.algoExpert.demo.Mapper;

import com.algoExpert.demo.Dto.CommentDto;
import com.algoExpert.demo.Entity.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    @Autowired
    private ModelMapper modelMapper;
    public Comment commentDtoToComment(CommentDto commentDto){return modelMapper.map(commentDto,Comment.class);}
    public CommentDto commentToCommentDto(Comment comment){return modelMapper.map(comment,CommentDto.class);}

    public List<CommentDto> commentDtos(List<Comment>comments){
        return comments.stream()
                .map(comment -> modelMapper.map(comment,CommentDto.class))
                .collect(Collectors.toList());
    }
}
