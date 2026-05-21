package com.axity.dinosaurpark.model;

import com.axity.dinosaurpark.zone.PowerPlant;

import java.util.List;
import java.util.Optional;

public class Technician extends Worker{
    protected Technician(int id, String name, double dailySalary) {
        super(id, name, dailySalary);
    }

    @Override
    public String getRole() {
        return "TECHNICIAN";
    }

    public void repairIfNeeded(PowerPlant plant, List<Vehicle> vehicles) {
        if (!plant.isOperational()) {
            Optional<Vehicle> available = vehicles
                    .stream()
                    .filter(v -> v.getStatus() == VehicleStatus.AVAILABLE)
                    .findFirst();

            if (available.isPresent()) {
                available.get().use();
                plant.repair();
                available.get().free();
            }
        }
    }
}
