package com.algoExpert.demo.OAuth2;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsManager {
    // method to create a user
    void createUser(AppUser user);

    // method to update a user
    void updateUser(AppUser user);

    // method to delete a user by username
    void deleteUser(String username);

    // method to change password
    void changePassword(String oldPassword, String newPassword);

    // method to check if a user exists by username
    boolean userExists(String username);

    // method to handle OAuth2 Login
    OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2LoginHandler();

    // method to handle OIDC Login
    OAuth2UserService<OidcUserRequest, OidcUser> oidcLoginHandler();
}
