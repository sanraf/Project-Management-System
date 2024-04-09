package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.Service.ProjectUserService;
import com.algoExpert.demo.Repository.UserRepository;
import com.algoExpert.demo.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectUserImpl implements ProjectUserService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findProject(int project_id) throws InvalidArgument {
        Project foundProject= projectRepository.findById(project_id).orElseThrow(() -> new InvalidArgument("Project with ID " + project_id + " not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User projectUser = null;

        if (authentication != null) {
            projectUser = (User) authentication.getPrincipal();
        }

        //clear the roles of the user who is loading the project
        List<Role> roleList = projectUser.getRoles();
        roleList.clear();
//        //assign the role of the user that all users should have
        roleList.add(Role.valueOf("USER"));

        for (Member member : foundProject.getMemberList() ){
            if(member.getProjectRole().equals("OWNER") && member.getUser_id().equals(projectUser.getUser_id()) ){
                //find if the member has a role of an onwer and assign owner role if you find
                roleList.add(Role.valueOf("OWNER"));
            }
            else if(member.getUser_id().equals(projectUser.getUser_id()) && member.getProjectRole().equals("MEMBER")){
                //assign role of a member if you find the id matching the user id who is loading the project
                roleList.add(Role.valueOf("MEMBER"));
            }
        }
        projectUser.setRoles(roleList);
        return userRepository.save(projectUser);
    }
}
