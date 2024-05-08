package com.algoExpert.demo.OAuth2;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Data
@Builder
public class AppUser implements UserDetails, OidcUser {
    //    custom fields
    String username;

    String fullName;

    String password;

    String email;

    String user_id;

    String image_url;

    @Enumerated(EnumType.STRING)
    LoginProvider provider;

    Map<String, Object> attributes;

    Collection<? extends GrantedAuthority> authorities;

    //    oidc user
    OidcIdToken idToken;
    OidcUserInfo userInfo;
    Map<String, Object> claims;

    //    implemented methods
    @Override
    public String getName() {
        return Objects.nonNull(fullName) ? fullName : username;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getClaims() {
        return claims != null ? claims : Collections.emptyMap();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public OidcIdToken getIdToken() {
        return idToken;
    }
}
