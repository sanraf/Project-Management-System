package com.algoExpert.demo.Service;

import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    //    Invite member to project
    Member inviteMember(int project_id, int user_id) throws InvalidArgument;

    //    get all members
    List<Member> getAllMembers();

    //    get member id
    Integer findLoginMember(int user_id);
}
