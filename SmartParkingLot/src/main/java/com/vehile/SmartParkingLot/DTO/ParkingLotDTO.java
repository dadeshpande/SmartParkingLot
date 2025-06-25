package com.vehicle.smartparkinglot.DTO;

public class ParkingLotDTO {
    private Long parkingLotId;
    private String name;
    private String location;
    private int totalFloors;
    private int totalSlots;

    public ParkingLotDTO() {
    }

    public ParkingLotDTO(Long parkingLotId, String name, String location, int totalFloors, int totalSlots) {
        this.parkingLotId = parkingLotId;
        this.name = name;
        this.location = location;
        this.totalFloors = totalFloors;
        this.totalSlots = totalSlots;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }
}
