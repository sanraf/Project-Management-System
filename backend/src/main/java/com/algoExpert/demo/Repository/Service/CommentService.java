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

   //  create comment
    Task createComment(Comment comment, int task_id) throws InvalidArgument;

    //    get all comments
  List<Comment> getAllComments();

    // update comment by id
  CommentDto editComment(int commentId, CommentDto newCommentDto) throws InvalidArgument;

    //    delete comment by id
  List<Comment> deleteComment(int commentId);

}
