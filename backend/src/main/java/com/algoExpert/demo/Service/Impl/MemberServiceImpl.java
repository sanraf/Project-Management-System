package com.algoExpert.demo.Service.Impl;


import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.UserRepository;
import com.algoExpert.demo.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberRepository memberRepository;

    //    Invite member to project
    @Override
    public Member inviteMember(int project_id, int user_id) throws InvalidArgument {
        // check if user and project exist
        User user = userRepository.findById(user_id).orElseThrow(() ->
                new InvalidArgument("User wth ID " + user_id + " not found"));

        Project userProject = projectRepository.findById(project_id).orElseThrow(() ->
                new InvalidArgument("Project wth ID " + project_id + " not found"));

        // Initialize the members list if it's null
        List<Member> members = userProject.getMemberList();
        if (members == null) {
            members = new ArrayList<>();
        }

        // check if member exist
        boolean memberExist = members.stream()
                .map(Member::getUser_id)
                .anyMatch(id -> id == user_id);

        if (memberExist) {
            throw new InvalidArgument("User ID " + user_id + " is already a member");
        } else {
            // create a new member
            Member newMember = new Member(0, user.getUser_id(), userProject.getProject_id(), null);
            members.add(newMember);
            userProject.setMemberList(members);
            projectRepository.save(userProject);

            return memberRepository.save(newMember);
        }
    }

    //    get all members
    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    //    get member id
    @Override
    public Integer findLoginMember(int user_id) {
        List<Member> memberList = memberRepository.findAll();

        Optional<Member> optionalMember = memberList.stream()
                .filter(member -> member.getUser_id() == user_id)
                .findFirst();

        if (optionalMember.isPresent()) {
            return optionalMember.get().getMember_id();
        } else {
            // Handle case when member with given user_id is not found
            return null; // Or throw an exception, depending on your use case
        }
    }

}
