package com.vehicle.smartparkinglot.service;


import com.vehicle.smartparkinglot.DTO.ParkingSlotDTO;
import com.vehicle.smartparkinglot.Exception.ParkingSlotNotFoundException;
import com.vehicle.smartparkinglot.entity.ParkingFloor;
import com.vehicle.smartparkinglot.entity.ParkingSlot;
import com.vehicle.smartparkinglot.entity.Vehicle;
import com.vehicle.smartparkinglot.repository.ParkingSlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSlotService {

    @Autowired
    private ParkingSlotRepo parkingSlotRepo;

    public ParkingSlot findById(Long id) throws ParkingSlotNotFoundException {
        Optional<ParkingSlot> optionalParkingSlot = parkingSlotRepo.findById(id);
        if (optionalParkingSlot.isEmpty()) {
            throw new ParkingSlotNotFoundException("Exit Panel not found with id: " + id);
        }
        return optionalParkingSlot.get();
    }

    public String deleteById(Long id) throws ParkingSlotNotFoundException {
        if (id == null || !parkingSlotRepo.existsById(id)) {
            throw new ParkingSlotNotFoundException("Exit Panel not found with id: " + id);
        }
        parkingSlotRepo.deleteById(id);
        return "Deleted successfully";
    }

    public ParkingSlot updatePanel(ParkingSlot ParkingSlot) throws ParkingSlotNotFoundException {
        if (ParkingSlot.getParkingSlotId() == null || !parkingSlotRepo.existsById(ParkingSlot.getParkingSlotId())) {
            throw new ParkingSlotNotFoundException("Exit Panel not found with id: " + ParkingSlot.getParkingSlotId());
        }
        return parkingSlotRepo.save(ParkingSlot);
    }

    public ParkingSlot createParkingSlot(ParkingSlot parkingSlot) {
        try {
            return parkingSlotRepo.save(parkingSlot);
        }catch (Exception e){
            throw new RuntimeException("Error creating parking slot: " + e.getMessage());
        }
    }

    public List<ParkingSlotDTO> getAllParkingSlots() throws ParkingSlotNotFoundException {
        List<ParkingSlot> parkingSlots = parkingSlotRepo.findAll();
        if (parkingSlots.isEmpty()) {
            throw new ParkingSlotNotFoundException("No Parking Slots found");
        }
        List<ParkingSlotDTO> parkingSlotDTOs = parkingSlots.stream()
                .map(slot -> new ParkingSlotDTO(
                        slot.getSlotId(),  slot.getSlotNumber(), slot.isAvailable(), slot.getParkingSlotType()))
                .toList();
        return parkingSlotDTOs;
    }

    public ParkingSlot assignParkingSlot(Vehicle vehicle, Long parkingSlotId) throws ParkingSlotNotFoundException {
        Optional<ParkingSlot> optionalParkingSlot = parkingSlotRepo.findById(parkingSlotId);
        if (optionalParkingSlot.isEmpty()) {
            throw new ParkingSlotNotFoundException("Parking Slot not found with id: " + parkingSlotId);
        }
        ParkingSlot parkingSlot = optionalParkingSlot.get();
        if (!parkingSlot.isAvailable()) {
            throw new ParkingSlotNotFoundException("Parking Slot is not available");
        }

        Optional<Vehicle> optionalVehicle = Optional.ofNullable(vehicle);
        vehicle = optionalVehicle.get();
        parkingSlot.setAvailable(false);
        return parkingSlotRepo.save(parkingSlot);

    }

    public ParkingSlot releaseParkingSlot(Long parkingSlotId) throws ParkingSlotNotFoundException {
        Optional<ParkingSlot> optionalParkingSlot = parkingSlotRepo.findById(parkingSlotId);
        if (optionalParkingSlot.isEmpty()) {
            throw new ParkingSlotNotFoundException("Parking Slot not found with id: " + parkingSlotId);
        }
        ParkingSlot parkingSlot = optionalParkingSlot.get();
        parkingSlot.setAvailable(true);
        return parkingSlotRepo.save(parkingSlot);
    }

    public List<ParkingSlotDTO> getAvailableParkingSlots() throws ParkingSlotNotFoundException {
        List<ParkingSlot> availableSlots = parkingSlotRepo.findAll().stream()
                .filter(ParkingSlot::isAvailable)
                .toList();
        if (availableSlots.isEmpty()) {
            throw new ParkingSlotNotFoundException("No available parking slots found");
        }
        List<ParkingSlotDTO> availableSlotDTOs = availableSlots.stream()
                .map(slot -> new ParkingSlotDTO(
                        slot.getSlotId(), slot.getSlotNumber(), slot.isAvailable(), slot.getParkingSlotType()))
                .toList();
        return availableSlotDTOs;
    }
}