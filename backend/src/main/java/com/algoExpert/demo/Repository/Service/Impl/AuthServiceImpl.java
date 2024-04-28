package com.algoExpert.demo.Repository.Service.Impl;

import com.algoExpert.demo.AppNotification.AppEmailBuilder;
import com.algoExpert.demo.AppNotification.EmailHtmlLayout;
import com.algoExpert.demo.Records.AuthRequest;
import com.algoExpert.demo.Records.RegistrationRequest;
import com.algoExpert.demo.Dto.UserDto;
import com.algoExpert.demo.Entity.HttpResponse;
import com.algoExpert.demo.Entity.Member;
import com.algoExpert.demo.Entity.Project;
import com.algoExpert.demo.Entity.User;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.Jwt.JwtService;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.Service.AuthService;
import com.algoExpert.demo.Repository.UserRepository;
import com.algoExpert.demo.UserAccount.AccountInfo.Entity.AccountConfirmation;
import com.algoExpert.demo.UserAccount.AccountInfo.Repository.AccountConfirmationRepository;
import com.algoExpert.demo.UserAccount.AccountInfo.Service.Impl.AccountConfirmationServiceImpl;
import com.algoExpert.demo.role.Role;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.algoExpert.demo.AppUtils.AppConstants.TEMP_USER_EMAIL;
import static com.algoExpert.demo.AppUtils.AppConstants.USERNAME_ALREADY_EXIST;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private AccountConfirmationServiceImpl confirmationService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AppEmailBuilder appEmailBuilder ;
    @Autowired
    private EmailHtmlLayout emailHtmlLayout;
    @Autowired
    private AccountConfirmationRepository confirmationRepository ;
    @Value("${confirm.account.url}")
    String confirmLink;


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

    /**
     * Register a new user or verifies unactivated account or user.
     *  <p>
     * This method takes fullName,userName and password through request parameter to generate a new user.
     * Initially it validates if the user exist and is enabled,if true,then <b><i>username already exist message</i></b> is displayed from InvalidArgument Exception.
     * Or else if the user is present but not enabled,an email will be sent to a user to verify the account to be enabled to the system.
     * And if the user is not present,a new user will be created and email to verify the account will be sent to the user.
     * @apiNote This method is the implementation of AuthService interface.
     * <strong>@Transactional</strong> ensures data persistence since the user needs to be saved into a database
     * while the email is also been sent, <strong><b>it's all or nothing</b></strong>
     * @see RegistrationRequest
     * @see AuthService
     * @author Santos Rafaelo
     * @param request
     * @return User
     * @throws InvalidArgument if the user already registered in the system
     */
    @Transactional
    @Override
    public User registerUser(RegistrationRequest request) throws InvalidArgument, MessagingException, IOException {
        Optional<User> existingUser = userRepository.findByEmail(request.email());
        String link = confirmLink;

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            if (user.isEnabled()) {
                throw new InvalidArgument(String.format(USERNAME_ALREADY_EXIST, user.getEmail()));
            } else {
                log.info("New token is generated: {}", link);
                AccountConfirmation confirmation = confirmationService.findAccountByUserId(user.getUser_id());
                String token = UUID.randomUUID().toString();

                confirmation.setToken(token);
                confirmation.setCreatedAt(LocalDateTime.now());
                confirmation.setExpiresAt(LocalDateTime.now().plusMinutes(1L));
                confirmationService.saveToken(confirmation);

                log.info("delete method called: {}{}", link,user.getUser_id());
                //todo apply send notification method here
                log.info("Renewed token is generated: {}{}", link, confirmation.getToken());
                appEmailBuilder.sendEmailAccountConfirmation(TEMP_USER_EMAIL,
                        emailHtmlLayout.confirmAccountHtml(user.getFullName(),link+token));
                return user;
            }
        } else {
            List<Role> roleList = List.of(Role.USER);
            User user = User.builder()
                    .fullName(request.fullName())
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .roles(roleList)
                    .build();
            User savedUser = userRepository.save(user);
            String token = confirmationService.createToken(user);
            //todo apply send notification method here
            log.info("New Token: {}{}", link, token);
            appEmailBuilder.sendEmailAccountConfirmation(TEMP_USER_EMAIL,
                        emailHtmlLayout.confirmAccountHtml(user.getFullName(),link+token));

            return savedUser;
        }
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
                    .fullname(logegdUser.getFullName())
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

    @Override
    public HttpResponse login1(AuthRequest  request) {
        try {
            Authentication auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
            User logegdUser = null;
            String jwtToken = "";
            String refreshToken = "";

            if(auth != null  && auth.isAuthenticated()){
                logegdUser = (User)auth.getPrincipal();
                jwtToken = jwtService.generateToken(request.username());
                refreshToken = refreshTokenSevice.createRefreshToken(request.username()).getToken();
            }
            return HttpResponse.builder()
                    .timeStamp(LocalTime.now().toString())
                    .status(HttpStatus.OK)
                    .message("Login Successful")
                    .email(logegdUser.getEmail())
                    .token(jwtToken)
                    .fullname(logegdUser.getFullName())
                    .userId(logegdUser.getUser_id())
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (BadCredentialsException e) {
            return HttpResponse.builder()
                    .timeStamp(LocalTime.now().toString())
                    .message("Incorrect username or password")

                    .build();
        } catch (Exception e) {
            return HttpResponse.builder()
                    .timeStamp(LocalTime.now().toString())
                    .message("Login failed: " + e.getMessage())

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
