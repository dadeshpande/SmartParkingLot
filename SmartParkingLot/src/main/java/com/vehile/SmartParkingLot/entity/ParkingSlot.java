package com.vehicle.smartparkinglot.entity;

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
    private String vehicleLicensePlate;

    @ManyToOne
    private ParkingFloor parkingFloor;

    public ParkingSlot() {
    }

    public ParkingSlot(String slotNumber, boolean isAvailable) {
        this.slotNumber = slotNumber;
        this.isAvailable = isAvailable;
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

    public String getVehicleLicensePlate() {
        return vehicleLicensePlate;
    }

    public void setVehicleLicensePlate(String vehicleLicensePlate) {
        this.vehicleLicensePlate = vehicleLicensePlate;
    }

    public Long getParkingSlotId() {
        return slotId;
    }

    public void setParkingSlotId(Long id) {
        this.slotId = id;
    }

    public void setVehicleId(Object o) {
        if (o instanceof String) {
            this.vehicleLicensePlate = (String) o; // Assuming vehicleId is a license plate string
        } else {
            throw new IllegalArgumentException("Vehicle ID must be a String representing the license plate");
        }
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
}
