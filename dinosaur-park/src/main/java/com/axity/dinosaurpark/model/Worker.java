package com.axity.dinosaurpark.model;

public abstract class Worker {
    private final int id;
    private final String name;
    private final double dailySalary;

    protected Worker(int id, String name, double dailySalary) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The worker must have a name.");
        }

        if (dailySalary <= 0) {
            throw new IllegalArgumentException("The worker must have a salary.");
        }

        this.id = id;
        this.name = name;
        this.dailySalary = dailySalary;
    }

    public abstract String getRole();
}
