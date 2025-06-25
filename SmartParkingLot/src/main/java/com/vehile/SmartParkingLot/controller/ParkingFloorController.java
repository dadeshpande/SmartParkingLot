package com.vehicle.smartparkinglot.controller;

import com.vehicle.smartparkinglot.DTO.ParkingFloorDTO;
import com.vehicle.smartparkinglot.Exception.ParkingFloorNotFoundException;
import com.vehicle.smartparkinglot.entity.ParkingFloor;
import com.vehicle.smartparkinglot.entity.ParkingSlot;
import com.vehicle.smartparkinglot.service.ParkingFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingFloorController {

    @Autowired
    private ParkingFloorService parkingFloorService;

    @PostMapping("/createParkingFloors")
    public ParkingFloor createParkingFloor(@RequestBody ParkingFloor parkingFloor) {
        return parkingFloorService.createParkingFloor(parkingFloor);
    }

    @GetMapping("/getParkingFloors")
    public List<ParkingFloorDTO> getParkingFloors(@RequestParam(required = false) Long id) throws ParkingFloorNotFoundException {
        if(id == null) {
            return parkingFloorService.getAllParkingFloors();
        }

        List<ParkingFloorDTO> parkingFloors = parkingFloorService.findById(id);
        if (parkingFloors.isEmpty()) {
            throw new ParkingFloorNotFoundException("Parking Floor not found with id: " + id);
        }
        return parkingFloors;
    }

    @DeleteMapping("/deleteParkingFloor/{id}")
    public String deleteParkingFloor(@PathVariable Long id) throws ParkingFloorNotFoundException {
        return parkingFloorService.deleteById(id);
    }

    @PutMapping("/updateParkingFloor/{id}")
    public ParkingFloor updateParkingFloor(@PathVariable Long id, @RequestBody ParkingFloor parkingFloor) throws ParkingFloorNotFoundException {
        parkingFloor.setFloorId(id);
        return parkingFloorService.updatePanel(parkingFloor);
    }

    @PostMapping("/mapParkingLot/{id}/ParkingFloor")
    public ParkingFloor mapParkingFloorsToParkingLot(@PathVariable Long id, @RequestBody ParkingFloor parkingFloor) throws ParkingFloorNotFoundException {
        return parkingFloorService.mapParkingFloorToParkingLot(id, parkingFloor);
    }

    @ExceptionHandler(ParkingFloorNotFoundException.class)
    public ResponseEntity handleParkingFloorNotFoundException(ParkingFloorNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

}
