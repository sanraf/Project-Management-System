package com.algoExpert.demo.Repository.Service.Impl;


import com.algoExpert.demo.AppNotification.AppEmailBuilder;
import com.algoExpert.demo.AppNotification.EmailHtmlLayout;
import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.Service.MemberService;
import com.algoExpert.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.algoExpert.demo.AppUtils.AppConstants.*;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AppEmailBuilder appEmailBuilder;
    @Autowired
    private EmailHtmlLayout emailHtmlLayout;
    @Value("${project.invite.url}")
    String projectUrl;
    public static final String OWNER = "OWNER";
    public static final String MEMBER = "MEMBER";



    //    Invite member to project
    @Transactional
    @Override
    public Member inviteMember(int project_id, int user_id) throws InvalidArgument {
        // check if user and project exist
        User user = userRepository.findById(user_id).orElseThrow(() ->
                new InvalidArgument(String.format(USER_NOT_FOUND,user_id)));

        Project userProject = projectRepository.findById(project_id).orElseThrow(() ->
                new InvalidArgument(String.format(PROJECT_NOT_FOUND,project_id)));


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
            throw new InvalidArgument(String.format(ALREADY_A_MEMBER,user_id));
        } else {
            Member newMember;
            // create a new member

            String role = members.isEmpty() ? OWNER : MEMBER;
            newMember = new Member(0, user.getUser_id(), userProject.getProject_id(), user.getUsername(), role, null);
            members.add(newMember);
            userProject.setMemberList(members);
            projectRepository.save(userProject);


//
            if (newMember.getProjectRole().equals(OWNER)){
                appEmailBuilder.sendEmailInvite(TEMP_USER_EMAIL,emailHtmlLayout.buildProjectInviteEmail(user.getFullName(),""));
                log.info("Project has been Created successfully {}{} :",projectUrl,userProject.getTitle());
            }else {
                appEmailBuilder.sendEmailInvite(TEMP_USER_EMAIL,emailHtmlLayout.buildProjectInviteEmail(user.getFullName(),projectUrl+project_id));
                log.info("You have been invited to the project {}{} :",projectUrl,project_id);
            }

            return memberRepository.save(newMember);
        }


    }


    //    get all members
    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public List<User> searchMemberToInvite(String fullnameLetters) {
        return userRepository.findByFullname(fullnameLetters);
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



    public User someMethod() {
        // Get the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            // If using UsernamePasswordAuthenticationToken, cast it
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

            // Now you can retrieve the credentials (password) or principal (username)
            Object credentials = authenticationToken.getCredentials();
            Object principal = authenticationToken.getPrincipal();

            System.err.println(credentials);
            System.err.println(principal);

            // In most cases, you'll want to cast the principal to your UserDetails implementation
            // UserDetails userDetails = (UserDetails) principal;

            // You can also retrieve other details like authorities
            // Collection<? extends GrantedAuthority> authorities = authenticationToken.getAuthorities();

            // Now you have access to the authentication token details
        }
        return (User) authentication.getPrincipal();
    }




}
