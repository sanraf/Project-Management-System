package com.algoExpert.demo.OAuth2;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

//    @Value("${frontend.url}")
//    private String frontendUrl;

    // target URL here
    private final String targetUrl = "http://localhost:5173/oauth2login";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl(targetUrl);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

