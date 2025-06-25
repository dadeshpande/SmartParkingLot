package com.vehicle.smartparkinglot.controller;

import com.vehicle.smartparkinglot.Exception.ParkingFloorNotFoundException;
import com.vehicle.smartparkinglot.Exception.ParkingLotNotFoundException;
import com.vehicle.smartparkinglot.entity.ParkingFloor;
import com.vehicle.smartparkinglot.entity.ParkingLot;
import com.vehicle.smartparkinglot.entity.ParkingSlot;
import com.vehicle.smartparkinglot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("/createParkingLots")
    public ParkingLot createParkingLot(@RequestBody ParkingLot parkingLot) {
        return parkingLotService.createParkingLot(parkingLot);
    }

    @GetMapping("/getParkingLots")
    public List<ParkingLot> getParkingLots(@RequestParam(required = false) Long id) throws ParkingLotNotFoundException {
        if (id == null) {
            return parkingLotService.getAllParkingLots();
        }
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot pl = parkingLotService.findById(id);
        if (pl != null) {
            parkingLots.add(pl);
        }
        return parkingLots.isEmpty() ? List.of() : parkingLots;
    }

    @DeleteMapping("/deleteParkingLot/{id}")
    public String deleteParkingLot(@PathVariable Long id) throws ParkingLotNotFoundException {
        return parkingLotService.deleteById(id);
    }

    @PutMapping("/updateParkingLot/{id}")
    public ParkingLot updateParkingLot(@PathVariable Long id, @RequestBody ParkingLot parkingLot) throws ParkingLotNotFoundException {
        parkingLot.setParkingLotId(id);
        return parkingLotService.updatePanel(parkingLot);
    }

    @ExceptionHandler(ParkingLotNotFoundException.class)
    public ResponseEntity handleParkingLotNotFoundException(ParkingLotNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @PostMapping("/mappingParkingLot/{id}/EntryPanels")
    public void mappingEntryPanelToParkingLot(@PathVariable("parkingLotId") Long parkingLotId,
                                              @RequestParam("entryPanelId") List<Long> entryPanelIds) throws ParkingLotNotFoundException {
        parkingLotService.addEntryPanelToParkingLot(parkingLotId, entryPanelIds);
    }

    @PostMapping("/mappingExitPanelToParkingLot/{id}")
    public void mappingExitPanelToParkingLot(@PathVariable("parkingLotId") Long parkingLotId,
                                              @RequestParam("exitPanelIds") List<Long> exitPanelIds) throws ParkingLotNotFoundException {
        parkingLotService.addExitPanelToParkingLot(parkingLotId, exitPanelIds);
    }

    @PostMapping("/mapParkingLot/{id}/parkingFloors")
    public ParkingLot mapParkingSlotsToParkingFloor(@PathVariable Long id, @RequestBody List<ParkingFloor> parkingFloors) throws ParkingFloorNotFoundException, ParkingLotNotFoundException {
        return parkingLotService.mapParkingFloorsToParkingLot(id, parkingFloors);
    }
}
