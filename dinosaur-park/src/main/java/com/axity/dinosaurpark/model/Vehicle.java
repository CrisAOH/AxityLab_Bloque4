package com.axity.dinosaurpark.model;

import com.axity.dinosaurpark.config.ParkConfig;

import java.io.IOException;

public class Vehicle {
    private final int id;
    private VehicleStatus status = VehicleStatus.AVAILABLE;
    private int repairCountdown;
    private final int repairSteps;

    public Vehicle(int id, int repairSteps) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }

        if (repairSteps <= 0) {
            throw new IllegalArgumentException("Invalid repair time.");
        }

        this.id = id;
        this.repairSteps = repairSteps;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public boolean use() {
        if (status != VehicleStatus.AVAILABLE) {
            return false;
        }

        status = VehicleStatus.IN_USE;
        return true;
    }

    public boolean free() {
        if (status != VehicleStatus.IN_USE) {
            return false;
        }

        status = VehicleStatus.AVAILABLE;
        return true;
    }

    public boolean markBroken() {
        if (status == VehicleStatus.BROKEN) {
            return false;
        }

        status = VehicleStatus.BROKEN;
        repairCountdown = repairSteps;
        return true;
    }

    public void tick() {
        if (status == VehicleStatus.BROKEN) {
            repairCountdown--;

            if (repairCountdown <= 0) {
                status = VehicleStatus.AVAILABLE;
                repairCountdown = 0;
            }
        }
    }
}
