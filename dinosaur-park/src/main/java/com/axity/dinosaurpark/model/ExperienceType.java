package com.axity.dinosaurpark.model;

public enum ExperienceType {
    BASIC(1, 3, "enclosure.basic.entryFee"),
    PREMIUM(2, 4, "enclosure.premium.entryFee"),
    VIP(3, 5, "enclosure.vip.entryFee");

    private final int minPoints;
    private final int maxPoints;
    private final String key;

    ExperienceType(int minPoints, int maxPoints, String key) {
        this.minPoints = minPoints;
        this.maxPoints = maxPoints;
        this.key = key;
    }
}
