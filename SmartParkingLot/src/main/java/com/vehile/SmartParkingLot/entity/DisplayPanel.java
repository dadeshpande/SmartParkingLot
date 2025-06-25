package com.vehicle.smartparkinglot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DisplayPanel {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long displayPanelId;
    private String location;
    private boolean isOperational;

    public DisplayPanel() {
    }

    public DisplayPanel(String location, boolean isOperational) {
        this.location = location;
        this.isOperational = isOperational;
    }

    public Long getDisplayPanelId() {
        return displayPanelId;
    }

    public void setDisplayPanelId(Long displayPanelId) {
        this.displayPanelId = displayPanelId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public void setOperational(boolean operational) {
        isOperational = operational;
    }
}
