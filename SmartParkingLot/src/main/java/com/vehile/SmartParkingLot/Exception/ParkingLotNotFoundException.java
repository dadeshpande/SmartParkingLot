package com.vehicle.smartparkinglot.Exception;


public class ParkingLotNotFoundException extends Exception {

    public ParkingLotNotFoundException(String message) {
        super(message);
    }

    public ParkingLotNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
