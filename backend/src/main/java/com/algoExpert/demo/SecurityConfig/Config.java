package com.algoExpert.demo.SecurityConfig;


import com.algoExpert.demo.AuthService.UserDetailsServiceImpl;
import com.algoExpert.demo.ExceptionHandler.CustomAccessDeniedHandler;
import com.algoExpert.demo.Jwt.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static com.algoExpert.demo.role.Permission.*;
import static com.algoExpert.demo.role.Role.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;

@Configuration
@EnableWebSecurity
public class Config {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JwtAuthFilter authFilter;
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
                                        .requestMatchers("/auth/**","/confirm/account**","/recover/**","/accessDenied","/assignee/**").permitAll()
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
//                                        .requestMatchers("/assignee/**").hasAnyRole(OWNER.name(), MEMBER.name())
//                                        .requestMatchers(PUT,"/assignee/**").hasAnyAuthority(OWNER_UPDATE.getPermission(),MEMBER_UPDATE.getPermission())
//                                        .requestMatchers(GET, "/assignee/**").hasAnyAuthority(OWNER_READ.getPermission(),MEMBER_READ.getPermission())
//                                        .requestMatchers(POST, "/assignee/**").hasAnyAuthority(OWNER_CREATE.getPermission(),MEMBER_UPDATE.getPermission())
//                                        .requestMatchers(DELETE,"/assignee/**").hasAnyAuthority(OWNER_DELETE.getPermission(),MEMBER_DELETE.getPermission())
                                        .requestMatchers("/admin/**").hasAnyRole(USER.name()).anyRequest().authenticated()
                                        .and()
                                        .exceptionHandling(c->c.accessDeniedHandler(accessDeniedHandler()));
                            } catch (Exception e) {
                                throw new RuntimeException(e.getMessage());
                            }
                        }

                )
                //remember me configuration
                .rememberMe(httpSecurityRememberMeConfigurer -> httpSecurityRememberMeConfigurer
                        .tokenRepository(persistentTokenRepository())
                        .alwaysRemember(true).tokenValiditySeconds(60*60*30))

                .oauth2Login(Customizer.withDefaults())
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(Customizer.withDefaults())
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
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

}
