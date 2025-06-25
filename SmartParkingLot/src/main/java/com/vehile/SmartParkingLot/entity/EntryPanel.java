package com.vehicle.smartparkinglot.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class EntryPanel {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long entryPanelId;
    private String location;
    private boolean isOperational;
    @OneToMany
    private List<ParkingTicket> parkingTicket;
    @ManyToOne
    private ParkingLot parkingLot;

    public EntryPanel() {
    }

    public EntryPanel(Long panelId, String location, boolean isOperational) {
        this.entryPanelId = panelId;
        this.location = location;
        this.isOperational = isOperational;
    }


    public Long getEntryPanelId() {
        return entryPanelId;
    }

    public void setEntryPanelId(Long entryPanelId) {
        this.entryPanelId = entryPanelId;
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

    public void setId(Long entryPanelId) {
        this.entryPanelId = entryPanelId;
    }

}
