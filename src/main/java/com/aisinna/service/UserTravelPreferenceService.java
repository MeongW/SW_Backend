package com.aisinna.service;


import com.aisinna.domain.UserInfo;
import com.aisinna.domain.UserTravelPreference;
import com.aisinna.domain.enums.TravelPreference;
import com.aisinna.domain.enums.TravelPreferenceValueProvider;
import com.aisinna.dto.TravelPreferenceDTO;
import com.aisinna.dto.UserTravelPreferenceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserTravelPreferenceService {

    public UserTravelPreference save() {
        return null;
    }

    public List<UserTravelPreferenceDTO> getPreferencesByUserId(UserInfo userInfo) {

        return userInfo.getUserTravelPreferenceList().stream()
                .map(preference -> UserTravelPreferenceDTO.builder()
                                .preferenceType(preference.getPreferenceType())
                                .preferenceValue(preference.getPreferenceValue())
                        .priority(preference.getPriority())
                        .build())
                .toList();
    }

    public List<TravelPreferenceDTO> getAllPreferences() {
        return List.of(
                createPreferenceDTO("purpose", TravelPreference.Purpose.values()),
                createPreferenceDTO("destination", TravelPreference.Destination.values()),
                createPreferenceDTO("activity", TravelPreference.Activity.values()),
                createPreferenceDTO("budget", TravelPreference.Budget.values()),
                createPreferenceDTO("duration", TravelPreference.Duration.values())
        );
    }

    private <E extends Enum<E> & TravelPreferenceValueProvider> TravelPreferenceDTO createPreferenceDTO(String category, E[] values) {
        return new TravelPreferenceDTO(
                category,
                Arrays.stream(values)
                        .map(value -> new TravelPreferenceDTO.EnumResponse(value.name(), value.getValue()))
                        .toList()
        );
    }
}
