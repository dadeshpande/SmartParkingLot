package com.vehicle.smartparkinglot.Exception;


public class ParkingFloorNotFoundException extends Exception {

    public ParkingFloorNotFoundException(String message) {
        super(message);
    }

    public ParkingFloorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
