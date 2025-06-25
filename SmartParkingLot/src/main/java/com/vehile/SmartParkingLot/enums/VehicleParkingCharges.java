package com.vehicle.smartparkinglot.enums;

public enum VehicleParkingCharges {
    CAR(20.0),
    BIKE(10.0),
    TRUCK(50.0),
    BUS(30.0);

    private final double charge;

    VehicleParkingCharges(double charge) {
        this.charge = charge;
    }

    public double getCharge() {
        return charge;
    }
}
