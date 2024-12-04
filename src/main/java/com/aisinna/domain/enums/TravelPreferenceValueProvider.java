package com.aisinna.domain.enums;

public interface TravelPreferenceValueProvider {
    TravelPreference.TravelPreferenceType getType();
    String getValue();
}
