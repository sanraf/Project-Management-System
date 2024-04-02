package com.algoExpert.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TaskTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int table_id;
    private String table_name;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

}
