package com.algoExpert.demo.Jwt;

import com.algoExpert.demo.AuthService.UserDetailsServiceImpl;
import com.algoExpert.demo.Entity.HttpResponse;
import com.algoExpert.demo.ExceptionHandler.JwtTokenExpiredException;
import com.algoExpert.demo.ExceptionHandler.JwtTokenMalformedException;
import com.algoExpert.demo.ExceptionHandler.ObjectMapperConfig;
import com.algoExpert.demo.OAuth2.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalTime;

@Component
public class JwtAuthFilter  extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserService userDetailsService2;
    @Autowired
    private ObjectMapperConfig mapperConfig;



//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//        Cookie[] cookies = request.getCookies();
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            token = authHeader.substring(7);
//            username = jwtService.extractUsername(token);
//        }
//        else if(cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("jwtToken")) {
//                    token = cookie.getValue();
//                    break;
//                }
//            }
//            if (token != null) {
//                username = jwtService.extractUsername(token);
//            }
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            if (jwtService.validateToken(token, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        Cookie[] cookies = request.getCookies();

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            } else if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("jwtToken")) {
                        token = cookie.getValue();
                        break;
                    }
                }
                if (token != null) {
                    username = jwtService.extractUsername(token);
                }
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (JwtTokenMalformedException | JwtTokenExpiredException jwtException) {

            HttpResponse errorResponse = HttpResponse.builder()
                    .timeStamp(LocalTime.now().toString())
                    .status(HttpStatus.UNAUTHORIZED)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .message("Your session has expired please login")
                    .developerMessage(jwtException.getMessage())
                    .urlInstance(request.getServletPath())
                    .method(request.getMethod())
                    .build();
            String jsonResponse = mapperConfig.objectMapper().writeValueAsString(errorResponse);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
        }
    }

}
