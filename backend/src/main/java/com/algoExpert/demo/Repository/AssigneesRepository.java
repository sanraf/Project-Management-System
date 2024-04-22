package com.algoExpert.demo.Repository;

import com.algoExpert.demo.Entity.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssigneesRepository extends JpaRepository<Assignee, Integer> {
    @Query("SELECT DISTINCT new com.algoExpert.demo.Entity.Assignee(a.username) FROM Assignee a WHERE a.task_id=:taskId")
    List<Assignee> findByTaskId(Integer taskId);

   //todo also check is the status is not complete the send email
//    @Query("SELECT DISTINCT new com.algoExpert.demo.Entity.Assignee(a.username) FROM Assignee a WHERE a.task_id=:taskId AND a.")
//    List<Assignee> findByTaskId(Integer taskId);
}
