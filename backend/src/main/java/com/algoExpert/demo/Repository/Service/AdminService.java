package com.algoExpert.demo.Repository.Service;

import com.algoExpert.demo.Entity.Admin;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
  Admin getUsersStats();
  List<Object[]>newUsers();

}
