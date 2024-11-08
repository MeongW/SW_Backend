package com.aisinna.oauth2.repository;


import com.aisinna.oauth2.domain.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {

    Optional<SocialUser> findByEmail(String email);
}
