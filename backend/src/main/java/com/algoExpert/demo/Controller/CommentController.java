package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Dto.CommentDto;
import com.algoExpert.demo.Entity.Comment;
import com.algoExpert.demo.Entity.Task;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

//  create new comment using member and task id
    @PostMapping("/create/{task_id}")
    private Task createComment(@RequestBody Comment comment,@PathVariable int task_id)throws InvalidArgument {
        return commentService.createComment(comment,task_id);
    }

//    get all comments
    @GetMapping("/getAllComments")
    private List<Comment> getAllComments(){
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
