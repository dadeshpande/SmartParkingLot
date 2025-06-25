package com.vehicle.smartparkinglot.service;

import com.vehicle.smartparkinglot.Exception.ParkingTicketNotFoundException;
import com.vehicle.smartparkinglot.entity.ParkingTicket;
import com.vehicle.smartparkinglot.entity.Vehicle;
import com.vehicle.smartparkinglot.enums.VehicleParkingCharges;
import com.vehicle.smartparkinglot.enums.VehicleType;
import com.vehicle.smartparkinglot.repository.ParkingTicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;


@Service
public class ParkingTicketService {

    @Autowired
    private ParkingTicketRepo parkingTicketRepo;

    public Object findById(Long id) throws ParkingTicketNotFoundException {
        Optional<ParkingTicket> vehicle = parkingTicketRepo.findById(id);
        if (vehicle.isEmpty()) {
            throw new ParkingTicketNotFoundException("Exit Panel not found with id: " + id);
        }
        return vehicle.get();
    }


    public String deleteById(Long id) throws ParkingTicketNotFoundException {
        if (id == null || !parkingTicketRepo.existsById(id)) {
            throw new ParkingTicketNotFoundException("Exit Panel not found with id: " + id);
        }
        parkingTicketRepo.deleteById(id);
        return "Deleted successfully";
    }

    public ParkingTicket updatePanel(ParkingTicket ParkingTicket) throws ParkingTicketNotFoundException {
        if (ParkingTicket.getParkingTicketId() == null || !parkingTicketRepo.existsById(ParkingTicket.getParkingTicketId())) {
            throw new ParkingTicketNotFoundException("Exit Panel not found with id: " + ParkingTicket.getParkingTicketId());
        }
        return parkingTicketRepo.save(ParkingTicket);
    }

    public ParkingTicket createParkingTicket(ParkingTicket vehicle) {
        return parkingTicketRepo.save(vehicle);
    }

    public List<ParkingTicket> getAllParkingTickets() {
        return parkingTicketRepo.findAll();
    }

    public void addParkingTicketToSlot(Long parkingTicketId, Long parkingSlotId) {
        parkingTicketRepo.findById(parkingTicketId).ifPresent(parkingTicket -> {
            try {
                parkingTicket.setParkingSlotId(parkingSlotId);
            } catch (ParkingTicketNotFoundException e) {
                throw new RuntimeException(e);
            }
            parkingTicketRepo.save(parkingTicket);
        });
    }

    public void addParkingTicketToExitPanel(Long parkingTicketId, Long exitPanelId) {
        parkingTicketRepo.findById(parkingTicketId).ifPresent(parkingTicket -> {
            parkingTicket.setExitPanelId(exitPanelId);

            parkingTicket.setExitTime(java.time.LocalDateTime.now());
            Duration duration = Duration.between(parkingTicket.getEntryTime(), parkingTicket.getExitTime());
            VehicleType vehicleType = parkingTicket.getVehicle().getVehicleType();
            VehicleParkingCharges vehicleParkingCharges = VehicleParkingCharges.valueOf(vehicleType.name());

            parkingTicket.setParkingFee(vehicleParkingCharges.getCharge() * Math.ceil(duration.toHours()));

            parkingTicketRepo.save(parkingTicket);
        });
    }

    public void addParkingTicketToEntryPanel(Long parkingTicketId, Long entryPanelId) {
        parkingTicketRepo.findById(parkingTicketId).ifPresent(parkingTicket -> {
            parkingTicket.setEntryPanelId(entryPanelId);
            parkingTicket.setEntryTime(java.time.LocalDateTime.now());
            parkingTicketRepo.save(parkingTicket);
        });
    }

    public void addParkingTicketToVehicle(Long parkingTicketId, Vehicle vehicle) {
        Optional<ParkingTicket> optionalParkingTicket = parkingTicketRepo.findById(parkingTicketId);
        if (optionalParkingTicket.isPresent()) {
            ParkingTicket parkingTicket = optionalParkingTicket.get();
            parkingTicket.getVehicle().setVehicle(vehicle);
            parkingTicketRepo.save(parkingTicket);
        } else {
            throw new RuntimeException("Parking Ticket not found with id: " + parkingTicketId);
        }


    }
}