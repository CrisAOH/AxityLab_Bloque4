package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.model.Ticket;
import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;
import com.axity.dinosaurpark.persistence.DatabaseService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ArrivalZone implements ParkZone {
    private final Queue<Tourist> waitingQueue;
    private final Set<Tourist> currentTourists;
    private final int maxCapacity;
    private final double ticketPrice;
    private long ticketCounter;

    public ArrivalZone(int maxCapacity, double ticketPrice) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("The park must have a capacity.");
        }

        if (ticketPrice <= 0) {
            throw new IllegalArgumentException("The ticket must have a price.");
        }

        this.maxCapacity = maxCapacity;
        this.ticketPrice = ticketPrice;
        ticketCounter = 1;
        waitingQueue = new LinkedList<>();
        currentTourists = new HashSet<>();
    }

    @Override
    public String getName() {
        return "Arrival Zone";
    }

    @Override
    public boolean hasCapacity() {
        return getCurrentOccupancy() < getMaxCapacity();
    }

    @Override
    public int getCurrentOccupancy() {
        return currentTourists.size();
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

        if (currentTourists.contains(tourist)) {
            return;
        }

        tourist.setStatus(TouristStatus.IN_PARK);
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

    private boolean canJoinQueue(Tourist tourist) {
        if (currentTourists.contains(tourist)) {
            return false;
        }

        if (waitingQueue.contains(tourist)) {
            return false;
        }

        if (tourist.getStatus() == TouristStatus.ATTACKED) {
            return false;
        }

        if (tourist.getStatus() == TouristStatus.EXITED) {
            return false;
        }

        return true;
    }

    public void addToQueue(Tourist tourist) {
        if (tourist == null) {
            return;
        }

        if (!canJoinQueue(tourist)) {
            return;
        }

        waitingQueue.offer(tourist);
    }

    public void processBatch(int batchSize, double discount, DatabaseService db) {
        if (batchSize <= 0) {
            return;
        }

        if (discount < 0 || discount > 1) {
            return;
        }

        int availableSpots = getMaxCapacity() - getCurrentOccupancy();
        int waitingCount = waitingQueue.size();
        int maxProcessable = Math.min(waitingCount, availableSpots);
        int touristsToProcess = Math.min(batchSize, maxProcessable);

        for (int i = 0; i < touristsToProcess; i++) {
            Tourist tourist = waitingQueue.poll();

            if (tourist == null) {
                break;
            }

            double finalTicketPrice = ticketPrice * (1 - discount);
            tourist.spend(finalTicketPrice);
            Ticket ticket = new Ticket(ticketCounter, tourist.getId(), finalTicketPrice, ExperienceType.BASIC, LocalDateTime.now());
            ticketCounter = ticketCounter + 1;
            enter(tourist);
        }
    }
}
