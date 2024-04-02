package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Dto.CommentDto;
import com.algoExpert.demo.Dto.TaskDto;
import com.algoExpert.demo.Entity.Comment;
import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.CommentMapper;
import com.algoExpert.demo.Mapper.TaskMapper;
import com.algoExpert.demo.Repository.CommentRepository;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.TaskRepository;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {

    //    create comment
    TaskDto createComment(CommentDto commentDto, int member_id, int task_id) throws InvalidArgument;

    //  create comment
    Task createComment(Comment comment, int member_id, int task_id) throws InvalidArgument;

    //    get all comments
    public List<CommentDto> getAllComments();

    // update comment by id
    public CommentDto editComment(int commentId, CommentDto newCommentDto) throws InvalidArgument;

    //    delete comment by id
    public List<Comment> deleteComment(int commentId);

}
