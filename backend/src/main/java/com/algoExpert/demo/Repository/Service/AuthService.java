package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Records.RegistrationRequest;
import com.algoExpert.demo.Dto.UserDto;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {

    //    create user
    @Transactional
    User register(User user);

    @Transactional
    User registerUser(RegistrationRequest request) throws InvalidArgument, AccessDeniedException;
    // get all users
    List<User> getUsers();
    HttpResponse login (User user);
    //    delete user by id
    List<UserDto> deleteUser(int userId);


}
