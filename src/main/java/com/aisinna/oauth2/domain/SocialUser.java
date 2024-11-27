package com.aisinna.oauth2.domain;

import com.aisinna.domain.UserInfo;
import com.aisinna.domain.UserTravelPreference;
import com.aisinna.oauth2.domain.enums.Role;
import com.aisinna.oauth2.domain.enums.SocialType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_user_id", updatable = false)
    private Long userId;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "Invalid email format.")
    @Column(name = "social_user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "social_user_provider_id", nullable = false)
    private String providerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_user_login_type", nullable = false)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private Role userRole;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @Builder
    public SocialUser(String email, SocialType socialType, Role userRole, String providerId) {
        this.email = email;
        this.socialType = socialType;
        this.userRole = userRole;
        this.providerId = providerId;
    }

    public String getSocialType() {
        return socialType.name();
    }
}
