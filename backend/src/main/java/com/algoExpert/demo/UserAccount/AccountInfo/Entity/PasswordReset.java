package com.algoExpert.demo.UserAccount.AccountInfo.Entity;

import com.algoExpert.demo.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String passwordToken;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private String newPassword;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "user_id")
    private User user;
}
