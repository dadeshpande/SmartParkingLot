package com.vehicle.smartparkinglot.DTO;

import com.vehicle.smartparkinglot.entity.ParkingSlot;

import java.util.List;

public class ParkingFloorDTO {
    private Long floorId;
    private String floorNumber;
    private int totalSlots;
    private int availableSlots;

    //private ParkingLotDTO parkingLot;
    public ParkingFloorDTO() {
    }

    public ParkingFloorDTO(Long floorId, String floorNumber, int totalSlots, int availableSlots) {
        this.floorId = floorId;
        this.floorNumber = floorNumber;
        this.totalSlots = totalSlots;
        this.availableSlots = availableSlots;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public String getFloorNumber() {
        return floorNumber;
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
}
