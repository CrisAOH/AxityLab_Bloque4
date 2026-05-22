package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.persistence.DatabaseService;

import java.util.*;

public class BathroomZone implements ParkZone{
    //private final Set<Tourist> currentTourists;
    private final Map<Tourist, Integer> bathroomUsage;
    private final int useDurationSteps;
    private final int maxCapacity;
    private final double spaPrice;
    private final double spaProbability;

    public BathroomZone(int useDurationSteps, int maxCapacity, double spaPrice, double spaProbability) {
        if (useDurationSteps <= 0) {
            throw new IllegalArgumentException("Invalid use time.");
        }

        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("The bathroom must have a capacity.");
        }

        if (spaPrice < 0) {
            throw new IllegalArgumentException("The spa must have a valid price.");
        }

        if (spaProbability < 0 || spaProbability > 1) {
            throw new IllegalArgumentException("The probability must be between 0 and 1.");
        }

        //currentTourists = new HashSet<>();
        bathroomUsage = new HashMap<>();
        this.useDurationSteps = useDurationSteps;
        this.maxCapacity = maxCapacity;
        this.spaPrice = spaPrice;
        this.spaProbability = spaProbability;
    }

    @Override
    public String getName() {
        return "Bathroom Zone";
    }

    @Override
    public boolean hasCapacity() {
        return getCurrentOccupancy() < getMaxCapacity();
    }

    @Override
    public int getCurrentOccupancy() {
        return bathroomUsage.size();
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public void enter(Tourist tourist) {
        if (tourist == null) {
            return;
        }

        if (!hasCapacity()) {
            return;
        }

        if (bathroomUsage.containsKey(tourist)) {
            return;
        }

        bathroomUsage.put(tourist, useDurationSteps);
    }

    @Override
    public void exit(Tourist tourist) {
        if (tourist == null) {
            return;
        }

        if (!bathroomUsage.containsKey(tourist)) {
            return;
        }

        bathroomUsage.remove(tourist);
    }

    public void tryEnter(Tourist tourist, Random random, DatabaseService db) {
        if (tourist == null) {
            return;
        }

        if (random == null) {
            return;
        }

        if (db == null) {
            return;
        }

        if (!hasCapacity()) {
            return;
        }

        if (bathroomUsage.containsKey(tourist)) {
            return;
        }

        enter(tourist);

        tourist.recordVisit(getName());

        if (random.nextDouble() < spaProbability) {
            tourist.spend(spaPrice);
            // Persistencia
        }
    }

    public void tick() {
        List<Tourist> touristsToRemove = new ArrayList<>();

        for (Map.Entry<Tourist, Integer> occupiedSlot : bathroomUsage.entrySet()) {
            int remainingTime = occupiedSlot.getValue() - 1;

            occupiedSlot.setValue(remainingTime);

            if (remainingTime <= 0) {
                touristsToRemove.add(occupiedSlot.getKey());
            }
        }

        for (Tourist tourist : touristsToRemove) {
            exit(tourist);
        }
    }
}
