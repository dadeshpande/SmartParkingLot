package com.vehicle.smartparkinglot.repository;

import com.vehicle.smartparkinglot.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSlotRepo extends JpaRepository<ParkingSlot, Long> {
}
