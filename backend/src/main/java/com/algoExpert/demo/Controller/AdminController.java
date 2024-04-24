package com.algoExpert.demo.Controller;

import com.algoExpert.demo.Entity.Admin;
import com.algoExpert.demo.Repository.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/usersStats")
    public Admin findUsersStats(){
        return adminService.getUsersStats();
    }

    @GetMapping("/numberOfNewUsers")
    public List<Object[]> newUsers(){
        return  adminService.newUsers();
    }
}
