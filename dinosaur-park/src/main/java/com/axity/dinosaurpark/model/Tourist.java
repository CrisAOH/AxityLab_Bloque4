package com.axity.dinosaurpark.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tourist {
    private final int id;
    private final String name;
    private TouristStatus status = TouristStatus.WAITING;
    private double moneySpent;
    private final List<String> visitedZones = new ArrayList<>();

    public Tourist(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The tourist must have a name.");
        }

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TouristStatus getStatus() {
        return status;
    }

    public void setStatus(TouristStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Must enter a valid status.");
        }

        this.status = status;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public List<String> getVisitedZones() {
        return Collections.unmodifiableList(visitedZones);
    }

    public void spend(double money) {
        if (money <= 0) {
            return;
        }

        moneySpent = moneySpent + money;
    }

    public void recordVisit(String zone){
        if (zone == null || zone.isBlank()) {
            return;
        }

        visitedZones.add(zone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Tourist)) {
            return false;
        }

        Tourist touristObj = (Tourist)obj;
        return this.id == touristObj.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
