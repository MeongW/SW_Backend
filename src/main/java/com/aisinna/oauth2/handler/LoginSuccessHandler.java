package com.aisinna.oauth2.handler;

import com.aisinna.jwt.TokenType;
import com.aisinna.jwt.provider.JwtProvider;
import com.aisinna.oauth2.domain.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String accessToken = jwtProvider.createToken(authentication.getName(), TokenType.ACCESS_TOKEN);
        String refreshToken = jwtProvider.createToken(authentication.getName(), TokenType.REFRESH_TOKEN);

        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");

        response.addCookie(cookie);
        response.setHeader("Authorization", "Bearer " + accessToken);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("accessToken", accessToken);
//        responseBody.put("message", userDetails.getUser().getAbti() == null ? "Need ABTI" : "Login successful");

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
        response.getWriter().flush();

        System.out.println("refresh token: " + refreshToken + "\naccess token: " + accessToken);
    }
}
