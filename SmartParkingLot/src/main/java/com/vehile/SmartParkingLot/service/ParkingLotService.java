package com.vehicle.smartparkinglot.service;


import com.vehicle.smartparkinglot.Exception.ParkingLotNotFoundException;
import com.vehicle.smartparkinglot.entity.ParkingFloor;
import com.vehicle.smartparkinglot.entity.ParkingLot;
import com.vehicle.smartparkinglot.repository.ParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepo parkingLotRepo;

    public ParkingLot findById(Long id) throws ParkingLotNotFoundException {
        Optional<ParkingLot> optionalParkingLot = parkingLotRepo.findById(id);
        if(optionalParkingLot.isEmpty()) {
            throw new ParkingLotNotFoundException("Parking Lot not found with id: " + id);
        }
        return optionalParkingLot.get();
    }

    public String deleteById(Long id) throws ParkingLotNotFoundException {
        if (id == null || !parkingLotRepo.existsById(id)) {
            throw new ParkingLotNotFoundException("Exit Panel not found with id: " + id);
        }
        parkingLotRepo.deleteById(id);
        return "Deleted successfully";
    }

    public ParkingLot updatePanel(ParkingLot ParkingLot) throws ParkingLotNotFoundException {
        if (ParkingLot.getParkingLotId() == null || !parkingLotRepo.existsById(ParkingLot.getParkingLotId())) {
            throw new ParkingLotNotFoundException("Exit Panel not found with id: " + ParkingLot.getParkingLotId());
        }
        return parkingLotRepo.save(ParkingLot);
    }

    public ParkingLot createParkingLot(ParkingLot parkingLot) {
        return parkingLotRepo.save(parkingLot);
    }

    public List<ParkingLot> getAllParkingLots() throws ParkingLotNotFoundException {
        List<ParkingLot> parkingLots = parkingLotRepo.findAll();
        if (parkingLots.isEmpty()) {
            throw new ParkingLotNotFoundException("No Parking Lots found");
        }
        return parkingLots;
    }

    public void addEntryPanelToParkingLot(Long parkingLotId, List<Long> entryPanelIds) throws ParkingLotNotFoundException {
        Optional<ParkingLot> optionalParkingLot = parkingLotRepo.findById(parkingLotId);
        if (optionalParkingLot.isEmpty()) {
            throw new ParkingLotNotFoundException("Parking Lot not found with ID : " + parkingLotId);
        }
        ParkingLot parkingLot = optionalParkingLot.get();
        parkingLot.getEntryPanelIds().addAll(entryPanelIds);
        parkingLotRepo.save(parkingLot);
    }

    public void addExitPanelToParkingLot(Long parkingLotId, List<Long> exitPanelIds) throws ParkingLotNotFoundException {
        Optional<ParkingLot> optionalParkingLot = parkingLotRepo.findById(parkingLotId);
        if (optionalParkingLot.isEmpty()) {
            throw new ParkingLotNotFoundException("Parking Lot not found with ID : " + parkingLotId);
        }
        ParkingLot parkingLot = optionalParkingLot.get();
        parkingLot.getExitPanelIds().addAll(exitPanelIds);
        parkingLotRepo.save(parkingLot);
    }

    public ParkingLot mapParkingFloorsToParkingLot(Long id, List<ParkingFloor> parkingFloors) throws ParkingLotNotFoundException {
        Optional<ParkingLot> optionalParkingLot = parkingLotRepo.findById(id);
        if (optionalParkingLot.isEmpty()) {
            throw new ParkingLotNotFoundException("Parking Lot not found with id: " + id);
        }
        ParkingLot parkingLot = optionalParkingLot.get();
        parkingLot.getParkingFloors().addAll(parkingFloors);
        return parkingLotRepo.save(parkingLot);
    }
}