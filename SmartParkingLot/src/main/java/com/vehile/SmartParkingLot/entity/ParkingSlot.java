package com.vehicle.smartparkinglot.entity;

import com.vehicle.smartparkinglot.enums.ParkingSlotType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.security.PrivateKey;

@Entity
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long slotId;
    private String slotNumber;
    private boolean isAvailable;
    private ParkingSlotType parkingSlotType;

    @ManyToOne
    private ParkingFloor parkingFloor;

    public ParkingSlot() {
    }

    public ParkingSlot(String slotNumber, boolean isAvailable, ParkingFloor parkingFloor, ParkingSlotType parkingSlotType) {
        this.slotNumber = slotNumber;
        this.isAvailable = isAvailable;
        this.parkingFloor = parkingFloor;
        this.parkingSlotType = parkingSlotType;
    }

    public ParkingSlotType getParkingSlotType() {
        return parkingSlotType;
    }

    public void setParkingSlotType(ParkingSlotType parkingSlotType) {
        this.parkingSlotType = parkingSlotType;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Long getParkingSlotId() {
        return slotId;
    }

    public void setParkingSlotId(Long id) {
        this.slotId = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean b) {
        this.isAvailable = b; // Assuming 'available' means the slot is not occupied
    }

    public void setParkingFloorId(Long parkingFloorId) {
        if (this.parkingFloor == null) {
            this.parkingFloor = new ParkingFloor();
        }
        this.parkingFloor.setId(parkingFloorId); // Assuming ParkingFloor has a setId method
    }

    public void setVehicleId(Long vehicleId) {
        if (this.parkingFloor == null) {
            this.parkingFloor = new ParkingFloor();
        }
        // Assuming ParkingFloor has a method to set vehicleId
        this.parkingFloor.setVehicleId(vehicleId); // This is a placeholder; adjust as necessary
    }
}
