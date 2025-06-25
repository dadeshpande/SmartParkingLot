package com.vehicle.smartparkinglot.enums;

public enum VehicleType {
    CAR("Car"),
    BIKE("Bike"),
    TRUCK("Truck"),
    BUS("Bus");

    private final String type;

    VehicleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
