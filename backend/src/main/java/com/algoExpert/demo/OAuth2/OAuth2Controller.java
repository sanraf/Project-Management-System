package com.algoExpert.demo.OAuth2;

import com.algoExpert.demo.Entity.HttpResponse;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.Jwt.JwtService;
import com.algoExpert.demo.Repository.Service.Impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/oauth")
public class OAuth2Controller {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthServiceImpl authService;

    @GetMapping("/oauth2user")
    public String getUserProject(){
        return "this an oauth2 user";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = appUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

//    @GetMapping("/authenticate")
//    public JwtResponse authenticateAndGetToken(OAuth2AuthenticationToken authenticationToken) {
//        if (authenticationToken != null && authenticationToken.isAuthenticated()) {
////            String username = authenticationToken.getPrincipal().getAttribute("username");
//
//            // Assuming AppUser is your custom user class
//            AppUser appUser = (AppUser) authenticationToken.getPrincipal();
//
//            // Retrieve username directly from the AppUser object
//            String username = appUser.getUsername();
////            RefreshToken refreshToken = tokenSevice.createRefreshToken(username);
//
//
//            return JwtResponse.builder()
//                    .jwtToken(jwtService.generateToken(username))
//                    .refreshToken(refreshToken.getToken()).build();
//        } else {
//            throw new UsernameNotFoundException("Invalid user request!");
//        }
//    }

    @GetMapping("/userDetails")
    public String userDetails(Model model) {
        // Get the Authentication object from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username of the authenticated user
            String username = authentication.getName();

            // Add the username to the model to display it in the view
            Model user = model.addAttribute("username", username);

            return authentication.toString();
        } else {
            // If the user is not authenticated, redirect to the login page
            return "redirect:/login";
        }
    }

    @GetMapping("/socialLogin")
    public HttpResponse loginOauth2user() {
        // Get the Authentication object from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        String password = "";
        String username = "";
        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username of the authenticated user
//            AppUser oauth2User = (AppUser) authentication.getPrincipal();
            username = authentication.getName();
//            password = oauth2User.getPassword();
//            username = oauth2User.getUsername();
        }
        return authService.loginSocialUser(username);
    }
}
