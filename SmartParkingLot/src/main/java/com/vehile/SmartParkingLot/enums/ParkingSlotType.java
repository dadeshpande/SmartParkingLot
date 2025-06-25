package com.vehicle.smartparkinglot.enums;

public enum ParkingSlotType {
    Medium("Car"),
    Small("Bike"),
    ExtraLarge("Truck"),
    Large("Bus");

    private final String type;

    ParkingSlotType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
