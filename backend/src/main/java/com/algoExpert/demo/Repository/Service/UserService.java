package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Dto.UserDto;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.Mapper.UserMapper;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface UserService {

    //    create user
    UserDto create(UserDto userDto);

    // get all users
    List<UserDto> getUsers();

    //    delete user by id
    List<UserDto> deleteUser(int userId);

    //    get User Project Ids
    List<Project> getUserProjectIds(int userId);
}
