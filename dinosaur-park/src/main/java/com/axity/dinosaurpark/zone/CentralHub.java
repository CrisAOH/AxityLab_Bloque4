package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;
import com.axity.dinosaurpark.persistence.DatabaseService;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CentralHub implements ParkZone {
    private final Set<Tourist> currentTourists;
    private final double souvenirPrice;
    private final double souvenirProbability;

    public CentralHub(double souvenirPrice, double souvenirProbability) {
        this.souvenirPrice = souvenirPrice;
        this.souvenirProbability = souvenirProbability;
        currentTourists = new HashSet<>();
    }

    @Override
    public String getName() {
        return "Central Hub";
    }

    @Override
    public boolean hasCapacity() {
        return true;
    }

    @Override
    public int getCurrentOccupancy() {
        return currentTourists.size();
    }

    @Override
    public int getMaxCapacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void enter(Tourist tourist) {
        if (tourist == null) {
            return;
        }

        if (currentTourists.contains(tourist)) {
            return;
        }

        currentTourists.add(tourist);
    }

    @Override
    public void exit(Tourist tourist) {
        if (tourist == null) {
            return;
        }

        if (!currentTourists.contains(tourist)) {
            return;
        }

        currentTourists.remove(tourist);
    }

    public void visit(Tourist tourist, Random random, DatabaseService db) {
        if (tourist == null) {
            return;
        }

        if (random == null) {
            return;
        }

        if (db == null) {
            return;
        }

        enter(tourist);

        tourist.recordVisit(getName());

        if (random.nextDouble() < souvenirProbability) {
            tourist.spend(souvenirPrice);
            // Persistencia
        }

        exit(tourist);
    }
}
