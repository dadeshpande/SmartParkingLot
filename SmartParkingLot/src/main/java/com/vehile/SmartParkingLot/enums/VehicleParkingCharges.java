package com.vehicle.smartparkinglot.enums;

import java.time.Duration;

public enum VehicleParkingCharges {
    CAR(20.0),
    BIKE(10.0),
    TRUCK(50.0),
    BUS(30.0);

    private final double charge;

    VehicleParkingCharges(double charge) {
        this.charge = charge;
    }

    public static double calculateParkingFee(Duration duration, VehicleType vehicleType) {
        double chargePerHour = 0.0;

        switch (vehicleType) {
            case CAR:
                chargePerHour = CAR.getCharge();
                break;
            case BIKE:
                chargePerHour = BIKE.getCharge();
                break;
            case TRUCK:
                chargePerHour = TRUCK.getCharge();
                break;
            case BUS:
                chargePerHour = BUS.getCharge();
                break;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }

        long hours = duration.toHours();
        return hours * chargePerHour;
    }

    public double getCharge() {
        return charge;
    }
}
