package com.aisinna.service;

import com.aisinna.domain.UserInfo;
import com.aisinna.domain.enums.TravelPreference;
import com.aisinna.global.exception.TravelExceptionHandler;
import com.aisinna.global.exception.enums.ErrorMessage;
import com.aisinna.repository.UserInfoRepository;
import com.aisinna.domain.UserTravelPreference;
import com.aisinna.dto.UserTravelPreferenceDTO;
import com.aisinna.oauth2.domain.SocialUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public void addPreferences(UserInfo userInfo, List<UserTravelPreferenceDTO> preferences) {

        // 기존 preferences 삭제
        userInfo.clearPreferenceItem();

        // 중복 확인용 Map 초기화
        Map<TravelPreference.TravelPreferenceType, Set<Integer>> priorityCheckMap = new HashMap<>();
        Map<TravelPreference.TravelPreferenceType, Set<TravelPreference.TravelPreferenceValue>> valueCheckMap = new HashMap<>();

        for (UserTravelPreferenceDTO dto : preferences) {
            TravelPreference.TravelPreferenceType type = dto.getPreferenceType();
            int priority = dto.getPriority();
            TravelPreference.TravelPreferenceValue value = dto.getPreferenceValue();

            if (!priorityCheckMap.containsKey(type)) {
                priorityCheckMap.put(type, new HashSet<>());
            }

            if (!valueCheckMap.containsKey(type)) {
                valueCheckMap.put(type, new HashSet<>());
            }

            if (priorityCheckMap.get(type).contains(priority)) {
                throw new TravelExceptionHandler(ErrorMessage.DUPLICATION_PREFERENCE_PRIORITY);
            }

            if (valueCheckMap.get(type).contains(value)) {
                throw new TravelExceptionHandler(ErrorMessage.DUPLICATION_PREFERENCE_VALUE);
            }

            UserTravelPreference preference = UserTravelPreference.builder()
                    .preferenceType(type)
                    .preferenceValue(value)
                    .priority(priority)
                    .userInfo(userInfo)
                    .build();

            userInfo.addPreferenceItem(preference);

            priorityCheckMap.get(type).add(priority);
            valueCheckMap.get(type).add(value);
        }

        // 저장
        userInfoRepository.save(userInfo);
    }



    public void deleteUserInfo(SocialUser socialUser) {

        userInfoRepository.delete(socialUser.getUserInfo());
    }
}
