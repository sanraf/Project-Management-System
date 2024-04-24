package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.Dto.UserDto;
import com.algoExpert.demo.Entity.HttpResponse;
import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.Jwt.JwtService;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.Service.AuthService;
import com.algoExpert.demo.Repository.UserRepository;
import com.algoExpert.demo.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RefreshTokenSevice refreshTokenSevice;

//    @Autowired
//    UserMapper userMapper;

    //  create user
//    public UserDto create(UserDto userDto) {
////        User user = UserMapper.mapToUser(userDto);
//        User userResults = userRepository.save(user);
////        return UserMapper.mapToUserDto(userResults);
//    }

    @Override
    public User register(User user) {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         List <Role>  roleList =  user.getRoles();
         roleList.add(Role.valueOf("USER"));
         user.setRoles(roleList);
         return userRepository.save(user);
    }

    @Override
    public HttpResponse login(User user) {
        try {
            Authentication auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            User logegdUser = null;
            String jwtToken = "";
            String refreshToken = "";

            if(auth != null  && auth.isAuthenticated()){
                logegdUser = (User)auth.getPrincipal();
                jwtToken = jwtService.generateToken(user.getEmail());
                refreshToken = refreshTokenSevice.createRefreshToken(user.getEmail()).getToken();
            }
            return HttpResponse.builder()
                    .timeStamp(LocalTime.now().toString())
                    .status(HttpStatus.OK)
                    .message("Login Successful")
                    .email(logegdUser.getEmail())
                    .token(jwtToken)
                    .fullname(logegdUser.getFullname())
                    .userId(logegdUser.getUser_id())
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (BadCredentialsException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalTime.now().toString())
                    .message("Incorrect username or password")
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
        } catch (Exception e) {
            return HttpResponse.builder()
                    .timeStamp(LocalTime.now().toString())
                    .message("Login failed: " + e.getMessage())
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
        }
    }


    // get all users
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    //  delete user by id
    @Override
    public List<UserDto> deleteUser(int userId) {
        userRepository.deleteById(userId);
        return userRepository.findAll()
                .stream().map(user -> new UserDto(user.getUser_id(),
                        user.getUsername(),
                        user.getEmail())).collect(Collectors.toList());
    }

//    public List<User> deleteUser(int userId){
//        userRepository.deleteById(userId);
//        return userRepository.findAll();
//    }


}
