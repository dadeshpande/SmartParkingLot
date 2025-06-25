package com.vehicle.smartparkinglot.service;


import com.vehicle.smartparkinglot.DTO.ParkingFloorDTO;
import com.vehicle.smartparkinglot.Exception.ParkingFloorNotFoundException;
import com.vehicle.smartparkinglot.entity.ParkingFloor;
import com.vehicle.smartparkinglot.entity.ParkingSlot;
import com.vehicle.smartparkinglot.repository.ParkingFloorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingFloorService {

    @Autowired
    private ParkingFloorRepo parkingFloorRepo;

    public List<ParkingFloorDTO> findById(Long id) throws ParkingFloorNotFoundException {
        List<ParkingFloorDTO> parkingFloorDTOs = new ArrayList<>();
        if (id == null) {
            List<ParkingFloor> parkingFloors = parkingFloorRepo.findAll();
            if (parkingFloors.isEmpty()) {
                throw new ParkingFloorNotFoundException("No Parking Floors found");
            }
            for (ParkingFloor parkingFloor : parkingFloors) {
                parkingFloorDTOs.add(new ParkingFloorDTO(
                        parkingFloor.getFloorId(),
                        parkingFloor.getFloorNumber(),
                        parkingFloor.getTotalSlots(),
                        parkingFloor.getAvailableSlots()));
            }
            return parkingFloorDTOs;
        }
        Optional<ParkingFloor> optionalParkingFloor = parkingFloorRepo.findById(id);
        if (optionalParkingFloor.isEmpty()) {
            throw new ParkingFloorNotFoundException("Parking Floor not found with id: " + id);
        }
        ParkingFloor parkingFloor = optionalParkingFloor.get();
        ParkingFloorDTO parkingFloorDTO = new ParkingFloorDTO(
                parkingFloor.getFloorId(),
                parkingFloor.getFloorNumber(),
                parkingFloor.getTotalSlots(),
                parkingFloor.getAvailableSlots());
        parkingFloorDTOs.add(parkingFloorDTO);
        return parkingFloorDTOs;
    }

    public String deleteById(Long id) throws ParkingFloorNotFoundException {
        if (id == null || !parkingFloorRepo.existsById(id)) {
            throw new ParkingFloorNotFoundException("Exit Panel not found with id: " + id);
        }
        parkingFloorRepo.deleteById(id);
        return "Deleted successfully";
    }

    public ParkingFloor updatePanel(ParkingFloor ParkingFloor) throws ParkingFloorNotFoundException {
        if (ParkingFloor.getFloorId() == null || !parkingFloorRepo.existsById(ParkingFloor.getFloorId())) {
            throw new ParkingFloorNotFoundException("Exit Panel not found with id: " + ParkingFloor.getFloorId());
        }
        return parkingFloorRepo.save(ParkingFloor);
    }

    public ParkingFloor createParkingFloor(ParkingFloor parkingFloor) {
        return parkingFloorRepo.save(parkingFloor);
    }

    public List<ParkingFloorDTO> getAllParkingFloors() throws ParkingFloorNotFoundException {
        List<ParkingFloor> parkingFloors = parkingFloorRepo.findAll();
        if (parkingFloors.isEmpty()) {
            throw new ParkingFloorNotFoundException("No Parking Floors found");
        }
        List<ParkingFloorDTO> parkingFloorDTOs = parkingFloors.stream()
                .map(parkingFloor -> new ParkingFloorDTO(
                        parkingFloor.getFloorId(),
                        parkingFloor.getFloorNumber(),
                        parkingFloor.getTotalSlots(),
                        parkingFloor.getAvailableSlots()))
                .toList();
        return parkingFloorDTOs;
    }

    public ParkingFloor mapParkingSlotsToParkingFloor(Long id, List<ParkingSlot> parkingSlots) throws ParkingFloorNotFoundException {
        Optional<ParkingFloor> optionalParkingFloor = parkingFloorRepo.findById(id);
        if (optionalParkingFloor.isEmpty()) {
            throw new ParkingFloorNotFoundException("Parking Floor not found with id: " + id);
        }
        ParkingFloor parkingFloor = optionalParkingFloor.get();
        parkingFloor.getParkingSlots().addAll(parkingSlots);
        return parkingFloorRepo.save(parkingFloor);
    }

    public ParkingFloor mapParkingFloorToParkingLot(Long id, ParkingFloor parkingFloor) throws ParkingFloorNotFoundException {
        Optional<ParkingFloor> optionalParkingFloor = parkingFloorRepo.findById(parkingFloor.getFloorId());
        if (optionalParkingFloor.isEmpty()) {
            throw new ParkingFloorNotFoundException("Parking Floor not found with id: " + parkingFloor.getFloorId());
        }
        ParkingFloor parkingFloor1 = optionalParkingFloor.get();
        parkingFloor1.setParkingLotId(id);
        return parkingFloorRepo.save(parkingFloor1);
    }
}