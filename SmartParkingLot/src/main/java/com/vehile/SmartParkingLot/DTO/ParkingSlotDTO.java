package com.vehicle.smartparkinglot.DTO;

import com.vehicle.smartparkinglot.entity.ParkingFloor;
import com.vehicle.smartparkinglot.enums.ParkingSlotType;

public class ParkingSlotDTO {
    private Long slotId;
    private String slotNumber;
    private boolean isAvailable;
    private ParkingSlotType parkingSlotType; // Changed to String for simplicity

    public ParkingSlotDTO() {
    }

    public ParkingSlotDTO(Long slotId, String slotNumber, boolean isAvailable, ParkingSlotType parkingSlotType) {
        this.slotId = slotId;
        this.slotNumber = slotNumber;
        this.isAvailable = isAvailable;
        this.parkingSlotType = parkingSlotType;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public ParkingSlotType getParkingSlotType() {
        return parkingSlotType;
    }

    public void setParkingSlotType(ParkingSlotType parkingSlotType) {
        this.parkingSlotType = parkingSlotType;
    }
}
