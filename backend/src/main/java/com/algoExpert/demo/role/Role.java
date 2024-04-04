package com.algoExpert.demo.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.algoExpert.demo.role.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER(
            Set.of(
                    USER_CREATE
            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_DELETE,
                    ADMIN_UPDATE,
                    OWNER_CREATE,
                    OWNER_DELETE,
                    OWNER_UPDATE,
                    OWNER_READ,
                    MEMBER_CREATE,
                    MEMBER_DELETE,
                    MEMBER_UPDATE,
                    MEMBER_READ,
                    USER_CREATE
            )
    ),

    OWNER(
            Set.of(
                    OWNER_CREATE,
                    OWNER_DELETE,
                    OWNER_UPDATE,
                    OWNER_READ,
                    MEMBER_CREATE,
                    MEMBER_DELETE,
                    MEMBER_UPDATE,
                    MEMBER_READ
            )
    ),

    MEMBER(
            Set.of(
                    MEMBER_CREATE,
                    MEMBER_DELETE,
                    MEMBER_UPDATE,
                    MEMBER_READ
            )
    );

    //use set for no duplications

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
