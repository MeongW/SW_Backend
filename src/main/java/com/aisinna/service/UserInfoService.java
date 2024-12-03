package com.aisinna.service;

import com.aisinna.domain.UserInfo;
import com.aisinna.repository.UserInfoRepository;
import com.aisinna.domain.UserTravelPreference;
import com.aisinna.dto.UserTravelPreferenceDTO;
import com.aisinna.oauth2.domain.SocialUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public UserInfo createUserInfo(SocialUser socialUser) {
        UserInfo userInfo = UserInfo.builder()
                .socialUser(socialUser)
                .build();

        return userInfoRepository.save(userInfo);
    }

    @Transactional
    public UserInfo addPreferences(UserInfo userInfo, List<UserTravelPreferenceDTO> preferences) {

        // UserTravelPreference 생성 및 추가
        for (UserTravelPreferenceDTO dto : preferences) {
            UserTravelPreference preference = UserTravelPreference.builder()
                    .preferenceType(dto.getPreferenceType())
                    .preferenceValue(dto.getPreferenceValue())
                    .priority(dto.getPriority())
                    .build();

            userInfo.addPreferenceItem(preference);
        }

        // 저장
        return userInfoRepository.save(userInfo);
    }

    public void deleteUserInfo(SocialUser socialUser) {

        userInfoRepository.delete(socialUser.getUserInfo());
    }
}
