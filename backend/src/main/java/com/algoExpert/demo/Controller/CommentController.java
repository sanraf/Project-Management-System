package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Dto.CommentDto;
import com.algoExpert.demo.Dto.TaskDto;
import com.algoExpert.demo.Entity.Comment;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

//  create new comment using member and task id
    @PostMapping("/create/{member_id}/{task_id}")
    private TaskDto createComment(@RequestBody CommentDto commentDto, @PathVariable int member_id, @PathVariable int task_id)throws InvalidArgument {
        return commentService.createComment(commentDto,member_id,task_id);
    }

//    get all comments
    @GetMapping("/getAllComments")
    private List<CommentDto> getUsers(){
        return commentService.getAllComments();
    }


    @PutMapping("/editComment/{comment_id}")
    private CommentDto editComment(@PathVariable int comment_id,@RequestBody CommentDto commentDto) throws InvalidArgument{
        return commentService.editComment(comment_id, commentDto);
    }

    @DeleteMapping("/delete/{comment_id}")
    List<Comment> deleteComment(@PathVariable int comment_id){
        return commentService.deleteComment(comment_id);
    }

}
