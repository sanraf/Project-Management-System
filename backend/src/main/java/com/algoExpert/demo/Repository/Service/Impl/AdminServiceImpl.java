package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.Entity.Admin;
import com.algoExpert.demo.Repository.AdminRepository;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.Service.AdminService;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private  MemberRepository memberRepository;

    @Autowired
    private  AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Admin getUsersStats() {
        Optional<Admin> adminOne = adminRepository.findById(1);

        Admin admin;
        if(adminOne.isPresent()){
            admin = adminOne.get();
        }else {
            admin = new Admin();
        }
        admin.setNumber_of_owners(memberRepository.totalProjectOwners());
        admin.setNumber_of_members(memberRepository.totalProjectMembers());
        return adminRepository.save(admin);
    }

    @Override
    public List<Object[]> newUsers() {
        return userRepository.getNewUsers(2024);
    }


}
