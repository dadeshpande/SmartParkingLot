package com.vehicle.smartparkinglot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vehicle.smartparkinglot.Exception.ParkingTicketNotFoundException;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ParkingTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    @OneToOne(cascade = CascadeType.ALL)
    private ParkingSlot parkingSlot;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Vehicle vehicle;

    @Version
    private Integer version;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime entryTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime exitTime;
    private double parkingFee;
    @ManyToOne(cascade = CascadeType.ALL)
    private EntryPanel entryPanel;
    @ManyToOne(cascade = CascadeType.ALL)
    private ExitPanel exitPanel;

    public ParkingTicket() {
    }

    public ParkingTicket(Vehicle vehicle, LocalDateTime entryTime, LocalDateTime exitTime, double parkingFee) {
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.parkingFee = parkingFee;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public double getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(double parkingFee) {
        this.parkingFee = parkingFee;
    }

    public void setParkingTicketId(Long id) {
        this.ticketId = id;
    }

    public Long getParkingTicketId() {
        return ticketId;
    }

    public void setEntryPanelId(Long entryPanelId) {
        if (entryPanel == null) {
            entryPanel = new EntryPanel();
        }
        entryPanel.setId(entryPanelId);
    }

    public void setExitPanelId(Long exitPanelId) {
        if (exitPanel == null) {
            exitPanel = new ExitPanel();
        }
        exitPanel.setId(exitPanelId);
    }

    public void setParkingSlotId(Long parkingSlotId) {
        if (parkingSlot == null) {
            parkingSlot = new ParkingSlot();
        }
        parkingSlot.setParkingSlotId(parkingSlotId);
    }
}