package com.vehicle.smartparkinglot.entity;

import com.vehicle.smartparkinglot.Exception.ParkingTicketNotFoundException;
import com.vehicle.smartparkinglot.enums.VehicleType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long vehicleId;

    @OneToOne
    private ParkingTicket parkingTicket;

    private String ownerName;
    private VehicleType vehicleType;
    private String licensePlate;

    public Vehicle() {
    }

    public Vehicle(String ownerName, VehicleType vehicleType, String licensePlate) {
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setParkingSlotId(Long parkingSlotId) throws ParkingTicketNotFoundException {
        if (this.parkingTicket != null) {
            this.parkingTicket.setParkingSlotId(parkingSlotId);
        } else {
            throw new ParkingTicketNotFoundException("Parking ticket is not set for this vehicle.");
        }
    }

    public void setId(Long vehicleId) {
        if (this.vehicleId == null) {
            this.vehicleId = vehicleId;
        } else {
            throw new IllegalArgumentException("Vehicle ID is already set and cannot be changed.");
        }
    }

    public void setVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            this.vehicleId = vehicle.getVehicleId();
            this.ownerName = vehicle.getOwnerName();
            this.vehicleType = vehicle.getVehicleType();
            this.licensePlate = vehicle.getLicensePlate();
        } else {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
    }
}