package com.vehicle.smartparkinglot.repository;

import com.vehicle.smartparkinglot.entity.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingTicketRepo extends JpaRepository<ParkingTicket, Long> {

    // Additional query methods can be defined here if needed
}
