package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import org.springframework.stereotype.Service;

@Service
public interface ProjectUserService {
    User findProject(int project_id) throws InvalidArgument;
}
