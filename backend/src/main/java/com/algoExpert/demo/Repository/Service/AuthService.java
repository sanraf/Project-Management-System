package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Dto.UserDto;
import com.algoExpert.demo.Entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {

    //    create user
    User register(User user);

    // get all users
    List<User> getUsers();

    HttpResponse login (User user);

    //    delete user by id
    List<UserDto> deleteUser(int userId);

    //    get User Project Ids
    List<Project> getUserProjectIds(int userId);
}
