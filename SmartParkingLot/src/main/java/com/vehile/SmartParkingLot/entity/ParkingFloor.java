package com.vehicle.smartparkinglot.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ParkingFloor {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long floorId;
    private String floorNumber;
    private int totalSlots;
    private int availableSlots;

    @ManyToOne
    private ParkingLot parkingLot;
    @OneToMany(mappedBy = "parkingFloor", cascade = CascadeType.ALL)
    private List<ParkingSlot> parkingSlots;

    public ParkingFloor() {
    }

    public ParkingFloor(String floorNumber, int totalSlots, int availableSlots, ParkingLot parkingLot) {
        this.floorNumber = floorNumber;
        this.totalSlots = totalSlots;
        this.availableSlots = availableSlots;
        this.parkingLot = parkingLot;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    public void setId(Long parkingFloorId) {
        this.floorId = parkingFloorId;
    }

    public void setParkingLotId(Long id) {
        if (this.parkingLot == null) {
            this.parkingLot = new ParkingLot();
        }
        this.parkingLot.setLotId(id);
    }

    public Object getParkingLotId() {
        if (this.parkingLot != null) {
            return this.parkingLot.getLotId();
        }
        return null;
    }
}
