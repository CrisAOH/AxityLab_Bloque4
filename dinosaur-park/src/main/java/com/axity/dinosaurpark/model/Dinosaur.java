package com.axity.dinosaurpark.model;

public abstract class Dinosaur {
    private final int id;
    private final String name;
    private final String species;
    private DinosaurStatus status = DinosaurStatus.IN_ENCLOSURE;
    private final double feedingCostPerDay;

    protected Dinosaur(int id, String name, String species, double feedingCostPerDay) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The dinosaur must have a name.");
        }

        if (species == null || species.isBlank()) {
            throw new IllegalArgumentException("The dinosaur must have a species.");
        }

        if (feedingCostPerDay <= 0) {
            throw new IllegalArgumentException("A feeding cost must be specified.");
        }

        this.id = id;
        this.name = name;
        this.species = species;
        this.feedingCostPerDay = feedingCostPerDay;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public double getFeedingCostPerDay() {
        return feedingCostPerDay;
    }

    public DinosaurStatus getStatus() {
        return status;
    }

    public abstract String getDiet();
    public abstract double getDangerLevel();

    public void escape() {
        status = DinosaurStatus.ESCAPED;
    }

    public void recapture() {
        status = DinosaurStatus.RECAPTURED;
    }

    public void returnToEnclosure() {
        status = DinosaurStatus.IN_ENCLOSURE;
    }
}
