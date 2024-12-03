package com.aisinna.oauth2.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails, OAuth2User {

    private final SocialUser user;
    private Map<String, Object> attributes;

    public CustomUserDetails(SocialUser user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }


    @Override
    public Map<String, Object> getAttributes() { return attributes; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return AuthorityUtils.createAuthorityList(user.getUserRole().getKey());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getName() { return this.getUsername(); }
}
