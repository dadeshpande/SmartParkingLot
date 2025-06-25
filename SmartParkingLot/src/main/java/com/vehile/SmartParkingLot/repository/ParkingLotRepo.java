package com.vehicle.smartparkinglot.repository;

import com.vehicle.smartparkinglot.entity.ParkingFloor;
import com.vehicle.smartparkinglot.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepo extends JpaRepository<ParkingLot, Long> {

    // Additional query methods can be defined here if needed
}
