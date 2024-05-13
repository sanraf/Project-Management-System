package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.Admin.AdminEnums.FeatureType;
import com.algoExpert.demo.Admin.Repository.Service.FeatureService;
import com.algoExpert.demo.Dto.CommentDto;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Mapper.CommentMapper;
import com.algoExpert.demo.Repository.*;
import com.algoExpert.demo.Repository.Service.CommentService;
import com.algoExpert.demo.Repository.Service.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.algoExpert.demo.AppUtils.AppConstants.TASK_NOT_FOUND;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    ProjectUserImpl projectUser;
    @Autowired
    UserNotificationService notificationService;
    @Autowired
    FeatureService featureService;


    //    create comment
    @Override
    public Task createComment(Comment comment, int task_id) throws InvalidArgument {
        Task task = taskRepository.findById(task_id).orElseThrow(() -> new InvalidArgument(String.format(TASK_NOT_FOUND,task_id)));
        User user = userRepository.findById(projectUser.loggedInUserId()).get();
        comment.setUsername(user.getFullName());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
        comment.setDate_created(simpleDateFormat.format(new Date()));
        List<Comment> commentList = task.getComments();
        commentList.add(comment);

        task.setComments(commentList);

        List<Assignee>  assigneeList = task.getAssignees();

        for (Assignee assigned : assigneeList){
           Optional<User> assigneeUser = userRepository.findByEmail(assigned.getUsername());

           if(assigneeUser.isPresent()){
               User foundAssignedUser = assigneeUser.get();

            //************************** create and save notification ******************************//

               String notifMsg = "Comment has been added to task: " + task.getTitle();
               notificationService.createNotification(foundAssignedUser,notifMsg);

           }
        }

        //************************** call feature service ******************************//
        featureService.updateFeatureCount(FeatureType.COMMENT);
        return taskRepository.save(task);
    }

    //    get all comments
    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // update comment by id
    @Override
    public CommentDto editComment(int commentId, CommentDto newCommentDto) throws InvalidArgument {
        Comment comment = commentRepository.findById(commentId)
                .map(oldComment -> {
                    if (newCommentDto != null) {
                        Optional.ofNullable(newCommentDto.getCommentBody()).ifPresent(oldComment::setCommentBody);
                    }
                    return commentRepository.save(oldComment);
                }).orElseThrow(() -> new InvalidArgument("Comment ID " + commentId + " not fount"));
        return CommentMapper.mapToCommentDto(comment);

    }
    /*
    public Comment editComment(int commentId, Comment newComment) {
        return commentRepository.findById(commentId)
                .map(oldComment -> {
                    oldComment.setCommentBody(newComment.getCommentBody());
                    return commentRepository.save(oldComment);
                }).orElseThrow();
    }*/


    //    delete comment by id
    @Override
    public List<Comment> deleteComment(int commentId) {
        Comment findComment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("comment with Id " + commentId + " does not exist"));
        if (findComment != null) {
            commentRepository.deleteById(commentId);
            return commentRepository.findAll();
        }

        return null;
    }
    /*
    public List<Comment> deleteComment(int commentId) {
        Comment findComment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("comment with Id " + commentId + " does not exist"));
        if (findComment != null) {
            commentRepository.deleteById(commentId);
            return commentRepository.findAll();
        }

        return null;
    }*/

}