package com.aisinna.global.config;

import com.aisinna.jwt.filter.JwtAuthenticationFilter;
import com.aisinna.jwt.provider.JwtProvider;
import com.aisinna.oauth2.handler.LoginSuccessHandler;
import com.aisinna.oauth2.repository.SocialUserRepository;
import com.aisinna.oauth2.service.CustomOauth2UserService;
import com.aisinna.oauth2.service.CustomUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private final CustomOauth2UserService oAuth2UserService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final CustomUserDetailService userDetailService;
    private final ObjectMapper objectMapper;
    private final JwtProvider jwtProvider;
    private final SocialUserRepository socialUserRepository;


    @Bean
    protected SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(CsrfConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        ))
                .authorizeHttpRequests(request ->
                        request.anyRequest()
                                .permitAll()
                ).oauth2Login(oauth2 ->
                        oauth2.authorizationEndpoint(endpoint ->
                                        endpoint.baseUri(
                                                "/api/v1/oauth2/authorization"
                                        ))
                                .redirectionEndpoint(endpoint ->
                                        endpoint.baseUri(
                                                "/login/oauth2/code/**"
                                        ))
                                .userInfoEndpoint(endpoint ->
                                        endpoint.userService(
                                                oAuth2UserService
                                        ))
                                .successHandler(loginSuccessHandler)
                )
                .addFilterAfter(jwtAuthenticationFilter(), LogoutFilter.class);

        return httpSecurity.build();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(
                List.of(
                        "https://api.smartcheers.site",
                        "https://smartcheers.site",
                        "https://152.67.209.153:8080",
                        "http://152.67.209.153:8080",
                        "http://localhost:5173",
                        "http://localhost:8080",
                        "http://localhost:8081"
                )
        );
        corsConfiguration.setAllowedHeaders(
                List.of("*")
        );
        corsConfiguration.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "PATCH")
        );
        corsConfiguration.setExposedHeaders(
                List.of("*")
        );

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailService);

        return new ProviderManager(provider);
    }

    @Bean JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(
                jwtProvider,
                userDetailService
        );
    }


}
