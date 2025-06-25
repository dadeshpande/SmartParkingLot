package com.vehicle.smartparkinglot.controller;

import com.vehicle.smartparkinglot.DTO.ParkingSlotDTO;
import com.vehicle.smartparkinglot.Exception.ParkingSlotNotFoundException;
import com.vehicle.smartparkinglot.entity.ParkingSlot;
import com.vehicle.smartparkinglot.entity.Vehicle;
import com.vehicle.smartparkinglot.service.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ParkingSlotController {

    @Autowired
    private ParkingSlotService parkingSlotService;

    @PostMapping("/createParkingSlots")
    public ParkingSlot createParkingSlot(@RequestBody ParkingSlot parkingSlot) {
        return parkingSlotService.createParkingSlot(parkingSlot);
    }

    @GetMapping("/getParkingSlots")
    public List<ParkingSlotDTO> getParkingSlots(@RequestParam(required = false) Long id) throws ParkingSlotNotFoundException {
        if(id == null) {
            return parkingSlotService.getAllParkingSlots();
        }
        List<ParkingSlotDTO> parkingSlotDTOS = new ArrayList<>();
        Optional<ParkingSlot> optionalParkingSlot = Optional.ofNullable(parkingSlotService.findById(id));
        if (optionalParkingSlot.isPresent()) {
            ParkingSlot parkingSlot = optionalParkingSlot.get();
             parkingSlotDTOS.add(new ParkingSlotDTO(parkingSlot.getParkingSlotId(),
                     parkingSlot.getSlotNumber(),
                     parkingSlot.isAvailable(),
                    parkingSlot.getParkingSlotType()));
        } else {
            throw new ParkingSlotNotFoundException("Parking Slot not found with id: " + id);
        }
        return parkingSlotDTOS;
    }

    @DeleteMapping("/deleteparkingSlot/{id}")
    public String deleteparkingSlot(@PathVariable Long id) throws ParkingSlotNotFoundException {
        return parkingSlotService.deleteById(id);
    }

    @PutMapping("/updateparkingSlot/{id}")
    public ParkingSlot updateparkingSlot(@PathVariable Long id, @RequestBody ParkingSlot parkingSlot) throws ParkingSlotNotFoundException {
        parkingSlot.setParkingSlotId(id);
        return parkingSlotService.updatePanel(parkingSlot);
    }

    @PostMapping("/assignParkingSlot/{id}/Vehicle")
    public ParkingSlot assignParkingSlot(@PathVariable(name = "id") Long parkingSlotId, @RequestParam Vehicle vehicle) throws ParkingSlotNotFoundException {
        return parkingSlotService.assignParkingSlot(vehicle, parkingSlotId);
    }

    @PostMapping("/releaseParkingSlot")
    public ParkingSlot releaseParkingSlot(@RequestParam Long parkingSlotId) throws ParkingSlotNotFoundException {
        return parkingSlotService.releaseParkingSlot(parkingSlotId);
    }

    @GetMapping("/getAvailableParkingSlots")
    public List<ParkingSlotDTO> getAvailableParkingSlots() throws ParkingSlotNotFoundException {
        return parkingSlotService.getAvailableParkingSlots();
    }

    @ExceptionHandler(ParkingSlotNotFoundException.class)
    public ResponseEntity handleParkingTicketNotFoundException(ParkingSlotNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
