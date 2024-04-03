package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Dto.UserDto;
import com.algoExpert.demo.Entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    //    create user
    UserDto create(UserDto userDto);

    // get all users
    List<User> getUsers();

    //    delete user by id
    List<UserDto> deleteUser(int userId);

    //    get User Project Ids
    List<Project> getUserProjectIds(int userId);
}
