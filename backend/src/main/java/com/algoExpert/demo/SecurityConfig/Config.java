package com.algoExpert.demo.SecurityConfig;


import com.algoExpert.demo.AuthService.UserDetailsServiceImpl;
import com.algoExpert.demo.ExceptionHandler.CustomAccessDeniedHandler;
import com.algoExpert.demo.Jwt.JwtAuthFilter;
import com.algoExpert.demo.OAuth2.OAuth2LoginFailureHandler;
import com.algoExpert.demo.OAuth2.OAuth2LoginSuccessHandler;
import com.algoExpert.demo.OAuth2.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.algoExpert.demo.role.Permission.*;
import static com.algoExpert.demo.role.Role.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;

@Configuration
@EnableWebSecurity
@Log4j2
@RequiredArgsConstructor
public class Config {
    private final UserService userService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtAuthFilter authFilter;

    @Autowired
    private OAuth2LoginSuccessHandler auth2LoginSuccessHandler;
    @Autowired
    private OAuth2LoginFailureHandler auth2LoginFailureHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config =new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:5173/"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .authorizeHttpRequests(request -> {
                            try {
                                request
                                        .requestMatchers("/auth/**","/confirm/account**","/recover/**","/accessDenied","/assignee/**", "/error").permitAll()
                                        .requestMatchers("/project/**").hasAnyRole(USER.name(), MEMBER.name())
                                        .requestMatchers(POST, "/project/**").hasAnyAuthority(USER_CREATE.getPermission())
                                        .requestMatchers(PUT, "/project/**").hasAnyAuthority(OWNER_UPDATE.getPermission())
                                        .requestMatchers(DELETE, "/project/**").hasAnyAuthority(OWNER_DELETE.getPermission())
                                        .requestMatchers("/member/**").hasAnyRole(USER.name(), OWNER.name())
                                        .requestMatchers(DELETE,"/member/**").hasAnyAuthority(OWNER_DELETE.getPermission())
                                        .requestMatchers(PUT,"/member/**").hasAnyAuthority(OWNER_UPDATE.getPermission())
                                        .requestMatchers(GET, "/member/**").hasAnyAuthority(OWNER_READ.getPermission())
                                        .requestMatchers(POST, "/member/**").hasAnyAuthority(OWNER_CREATE.getPermission())
                                        .requestMatchers("/table/**").hasAnyRole(OWNER.name())
                                        .requestMatchers(PUT,"/table/**").hasAnyAuthority(OWNER_UPDATE.getPermission())
                                        .requestMatchers(GET, "/table/**").hasAnyAuthority(OWNER_READ.getPermission())
                                        .requestMatchers(POST, "/table/**").hasAnyAuthority(OWNER_CREATE.getPermission())
                                        .requestMatchers(DELETE,"/table/**").hasAnyAuthority(OWNER_DELETE.getPermission())
                                        .requestMatchers("/task/**").hasAnyRole(OWNER.name(), MEMBER.name())
                                        .requestMatchers(PUT,"/task/**").hasAnyAuthority(OWNER_UPDATE.getPermission(),MEMBER_UPDATE.getPermission())
                                        .requestMatchers(GET, "/task/**").hasAnyAuthority(OWNER_READ.getPermission(),MEMBER_READ.getPermission())
                                        .requestMatchers(POST, "/task/**").hasAnyAuthority(OWNER_CREATE.getPermission(),MEMBER_UPDATE.getPermission())
                                        .requestMatchers(DELETE,"/task/**").hasAnyAuthority(OWNER_DELETE.getPermission(),MEMBER_DELETE.getPermission())
                                        .requestMatchers("/user/**").hasAnyRole(USER.name())
                                        .requestMatchers("/comment/**").hasAnyRole(OWNER.name(), MEMBER.name())
                                        .requestMatchers(PUT,"/comment/**").hasAnyAuthority(OWNER_UPDATE.getPermission(),MEMBER_UPDATE.getPermission())
                                        .requestMatchers(GET, "/comment/**").hasAnyAuthority(OWNER_READ.getPermission(),MEMBER_READ.getPermission())
                                        .requestMatchers(POST, "/comment/**").hasAnyAuthority(OWNER_CREATE.getPermission(),MEMBER_UPDATE.getPermission())
                                        .requestMatchers(DELETE,"/comment/**").hasAnyAuthority(OWNER_DELETE.getPermission(),MEMBER_DELETE.getPermission())
                                        .requestMatchers("/assignee/**").hasAnyRole(OWNER.name(), MEMBER.name())
                                        .requestMatchers(PUT,"/assignee/**").hasAnyAuthority(OWNER_UPDATE.getPermission(),MEMBER_UPDATE.getPermission())
                                        .requestMatchers(GET, "/assignee/**").hasAnyAuthority(OWNER_READ.getPermission(),MEMBER_READ.getPermission())
                                        .requestMatchers(POST, "/assignee/**").hasAnyAuthority(OWNER_CREATE.getPermission(),MEMBER_UPDATE.getPermission())
                                        .requestMatchers(DELETE,"/assignee/**").hasAnyAuthority(OWNER_DELETE.getPermission(),MEMBER_DELETE.getPermission())
                                        .requestMatchers("/admin/**").hasAnyRole(USER.name()).anyRequest().authenticated();
                            } catch (Exception e) {
                                throw new AccessDeniedException(e.getMessage());
                            }
                        }

                )

//                .oauth2Login(Customizer.withDefaults())
                .oauth2Login(oc -> oc.userInfoEndpoint(ui -> ui.userService(userService.oauth2LoginHandler())
                        .oidcUserService(userService.oidcLoginHandler())
                )
                        .successHandler(auth2LoginSuccessHandler)
                        .failureHandler(auth2LoginFailureHandler)
                )
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authenticationProvider(authenticationProvider())
                .formLogin(Customizer.withDefaults())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
                 return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> successLogger() {
        return event -> {
            log.info("success: {}", event.getAuthentication());
        };
    }
}
