
package com.algoExpert.demo.Entity;

import com.algoExpert.demo.OAuth2.LoginProvider;
import com.algoExpert.demo.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
//@Table(name ="pms_users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
//    private Long user_id;

    @NotBlank(message = "fullName required")
    private String fullName;

    @Column(unique = true)
//    @Email(message = "Invalid user email")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @Enumerated(EnumType.STRING)
    private List<Role> roles;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<UserNotification> userNotificationList;

    private LocalDate dateRegistered;
    private boolean locked = false;
    private boolean enabled = false;

    //    additional fields
    private String image_url;
    private String username;
    private LocalDateTime created_at;

    @Enumerated(EnumType.STRING)
    private LoginProvider provider;

    @PrePersist
    void assignCreatedAt(){
        this.created_at = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.addAll(role.getAuthorities());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        if (email != null){
            username = email;
            return username;
        } else {
            return username;
        }
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
