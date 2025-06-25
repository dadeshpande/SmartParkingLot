package com.vehicle.smartparkinglot.repository;

import com.vehicle.smartparkinglot.entity.ParkingFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingFloorRepo extends JpaRepository<ParkingFloor, Long> {

    // Additional query methods can be defined here if needed
}
