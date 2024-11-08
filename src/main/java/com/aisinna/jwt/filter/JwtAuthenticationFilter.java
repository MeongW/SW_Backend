package com.aisinna.jwt.filter;

import com.aisinna.jwt.provider.JwtProvider;
import com.aisinna.oauth2.domain.CustomUserDetails;
import com.aisinna.oauth2.service.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final CustomUserDetailService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {

            String token = jwtProvider.parseToken(request);

            if (token != null) {
                String userEmail = jwtProvider.validate(token);

                if (userEmail == null) {

                    filterChain.doFilter(request, response);

                    return;
                }

                CustomUserDetails customUserDetails = userDetailsService.loadUserByUsername(userEmail);

                if (SecurityContextHolder.getContext().getAuthentication() == null) {

                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            customUserDetails,
                            null,
                            customUserDetails.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } else {

                filterChain.doFilter(request, response);

                return;
            }
        } catch (Exception e) {

            log.info("JwtAuthenticationFilter_doFilterInternal", e);
        }

        filterChain.doFilter(request, response);
    }



}
