package com.axity.dinosaurpark.model;

public class SatisfactionSurvey {
    private final int touristId;
    private final String enclosureName;
    private final int score;

    public SatisfactionSurvey(int touristId, String enclosureName, int score) {
        if (touristId <= 0) {
            throw new IllegalArgumentException("The survey must be associated to a tourist.");
        }

        if (enclosureName == null || enclosureName.isBlank()) {
            throw new IllegalArgumentException("An enclosure name is needed.");
        }

        if (score <= 0 || score > 5) {
            throw new IllegalArgumentException("The score must be between 1 and 5.");
        }

        this.touristId = touristId;
        this.enclosureName = enclosureName;
        this.score = score;
    }

    public int getTouristId() {
        return touristId;
    }

    public String getEnclosureName() {
        return enclosureName;
    }

    public int getScore() {
        return score;
    }
}
