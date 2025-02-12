package com.algoExpert.demo.Repository;


import com.algoExpert.demo.Entity.TaskContainer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


@Repository
public interface TableRepository extends JpaRepository<TaskContainer, Integer> {

    @Query("SELECT COUNT(t) FROM TaskContainer t")
    Integer countTables();


        Page<TaskContainer> findByProjectIdAndTableNameContainingIgnoreCase(Integer projectId, String tableName, Pageable pageable);
        Page<TaskContainer> findByProjectId(Integer projectId, Pageable pageable);
        long countByProjectId( Integer id);
}
