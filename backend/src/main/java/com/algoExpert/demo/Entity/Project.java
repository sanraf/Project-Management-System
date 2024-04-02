package com.algoExpert.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer project_id;

    @NotBlank(message = "project title required")
    private String title;

    private String description;

//  relationships
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskTable> tables;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> memberList;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

}
