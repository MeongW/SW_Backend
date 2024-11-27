package com.aisinna.domain.enums;

public enum Emotion {
    BAD,
    GOOD,
    BEST,
    ;

    public String getKey() {
        return name();
    }
}
