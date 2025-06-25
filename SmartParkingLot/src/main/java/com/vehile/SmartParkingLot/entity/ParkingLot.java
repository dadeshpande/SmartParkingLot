package com.vehicle.smartparkinglot.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long LotId;
    private String name;
    private String location;
    private int totalFloors;
    private int totalSlots;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<ParkingFloor> parkingFloors;

    @OneToMany
    private List<EntryPanel> entryPanels;
    @OneToMany
    private List<ExitPanel> exitPanels;

    public ParkingLot() {
    }

    public ParkingLot(String name, String location, int totalFloors, int totalSlots) {
        this.name = name;
        this.location = location;
        this.totalFloors = totalFloors;
        this.totalSlots = totalSlots;
    }

    public Long getLotId() {
        return LotId;
    }

    public void setLotId(Long lotId) {
        LotId = lotId;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public void setParkingFloors(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public Long getParkingLotId() {
        return LotId;
    }

    public void setParkingLotId(Long id) {
        this.LotId = id;
    }

    public List<ExitPanel> getExitPanels() {
        return exitPanels;
    }

    public List<EntryPanel> getEntryPanels() {
        return entryPanels;
    }

    public void setVehicleId(Long vehicleId) {
        if (this.entryPanels == null) {
            this.entryPanels = List.of(new EntryPanel());
        }
        if (this.exitPanels == null) {
            this.exitPanels = List.of(new ExitPanel());
        }
    }
}
