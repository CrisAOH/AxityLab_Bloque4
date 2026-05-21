package com.axity.dinosaurpark.model;

import java.time.LocalDateTime;

public class Ticket {
    private final long id;
    private final int touristId;
    private final double price;
    private final ExperienceType category;
    private final LocalDateTime issuedAt;

    public Ticket(long id, int touristId, double price, ExperienceType category, LocalDateTime issuedAt) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }

        if (touristId <= 0) {
            throw new IllegalArgumentException("The ticket must be associated to a tourist.");
        }

        if (price <= 0.0) {
            throw new IllegalArgumentException("The ticket must have a valid price.");
        }

        if (category == null) {
            throw new IllegalArgumentException("The ticket must have a category.");
        }

        if (issuedAt == null) {
            throw new IllegalArgumentException("The ticket must have an issued date.");
        }

        this.id = id;
        this.touristId = touristId;
        this.price = price;
        this.category = category;
        this.issuedAt = issuedAt;
    }

    public long getId() {
        return id;
    }

    public int getTouristId() {
        return touristId;
    }

    public double getPrice() {
        return price;
    }

    public ExperienceType getCategory() {
        return category;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }
}
