package com.vehicle.smartparkinglot.Exception;


public class ParkingSlotNotFoundException extends Exception {

    public ParkingSlotNotFoundException(String message) {
        super(message);
    }

    public ParkingSlotNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
