package com.vehicle.smartparkinglot.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ExitPanel {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long exitPanelId;
    private String location;
    private boolean isOperational;
    @OneToMany
    private List<ParkingTicket> parkingTicket;
    @ManyToOne
    private ParkingLot parkingLot;

    public ExitPanel() {
    }

    public ExitPanel(Long panelId, String location, boolean isOperational) {
        this.exitPanelId = panelId;
        this.location = location;
        this.isOperational = isOperational;
    }

    public Long getExitPanelId() {
        return exitPanelId;
    }

    public void setExitPanelId(Long exitPanelId) {
        this.exitPanelId = exitPanelId;
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

    public void setId(Long exitPanelId) {
        this.exitPanelId = exitPanelId;
    }
}
