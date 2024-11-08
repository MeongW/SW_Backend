package com.aisinna.oauth2.service;

import com.aisinna.oauth2.domain.CustomUserDetails;
import com.aisinna.oauth2.domain.enums.Role;
import com.aisinna.oauth2.domain.enums.SocialType;
import com.aisinna.oauth2.domain.SocialUser;
import com.aisinna.oauth2.repository.SocialUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final SocialUserRepository socialUserRepository;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        String oauthClientName = oAuth2UserRequest.getClientRegistration().getClientName();

        SocialUser socialUser = null;
        String userId = "";
        String userName = "";
        String userEmail = "";

        if (oauthClientName.equals("kakao")) {
            Map<String, Object> attributes = oAuth2User.getAttributes();

            userId = ((Long) attributes.get("id")).toString();
            LinkedHashMap linkedHashMap = (LinkedHashMap) attributes.get("properties");
            userName = (String) linkedHashMap.get("nickname");
            linkedHashMap = (LinkedHashMap) attributes.get("kakao_account");
            userEmail = (String) linkedHashMap.get("email");

            socialUser = SocialUser.builder().email(userEmail).socialType(SocialType.KAKAO).providerId(userId).userRole(Role.USER).build();
        }

        else if(oauthClientName.equals("google")) {
            userId = oAuth2User.getAttribute("sub");
            userEmail = oAuth2User.getAttribute("email");
            userName = oAuth2User.getAttribute("name");
            socialUser = SocialUser.builder().email(userEmail).socialType(SocialType.GOOGLE).providerId(userId).userRole(Role.USER).build();
        }
        else {
            throw new IllegalArgumentException("Unsupported social client name: " + oauthClientName);
        }

        if (socialUser == null) {
            throw new IllegalStateException("Failed to find or create social user.");
        }

        Optional<SocialUser> existingUser = socialUserRepository.findByEmail(socialUser.getEmail());
        if (existingUser.isPresent() && existingUser.get().getSocialType().equals(socialUser.getSocialType())) {
            return new CustomUserDetails(socialUser, oAuth2User.getAttributes());
        }

        socialUserRepository.save(socialUser);

        return new CustomUserDetails(socialUser, oAuth2User.getAttributes());
    }
}
