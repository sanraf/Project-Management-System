
package com.algoExpert.demo.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer user_id;
 @NotBlank(message = "username required")
 private String username;
 @Email(message = "invalid user email")
 private String email;

 public User() {
 }

 public User(Integer user_id, String username, String email) {
  this.user_id = user_id;
  this.username = username;
  this.email = email;
 }

 public Integer getUser_id() {
  return user_id;
 }

 public void setUser_id(Integer user_id) {
  this.user_id = user_id;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

}

