package com.vehicle.smartparkinglot.repository;

import com.vehicle.smartparkinglot.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    // Additional query methods can be defined here if needed
}
