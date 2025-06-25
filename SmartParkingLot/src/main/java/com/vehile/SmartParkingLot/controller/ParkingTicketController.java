package com.vehicle.smartparkinglot.controller;

import com.vehicle.smartparkinglot.Exception.ParkingTicketNotFoundException;
import com.vehicle.smartparkinglot.Exception.VehicleNotFoundException;
import com.vehicle.smartparkinglot.entity.ParkingTicket;
import com.vehicle.smartparkinglot.entity.Vehicle;
import com.vehicle.smartparkinglot.service.ParkingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ParkingTicketController {

    @Autowired
    private ParkingTicketService parkingTicketService;

    @PostMapping("/createParkingTickets")
    public ParkingTicket createParkingTicket(@RequestBody ParkingTicket parkingTicket) {
        return parkingTicketService.createParkingTicket(parkingTicket);
    }

    @GetMapping("/getParkingTickets")
    public List<ParkingTicket> getParkingTickets(@RequestParam(required = false) Long id) throws ParkingTicketNotFoundException {
        if(id == null) {
            return parkingTicketService.getAllParkingTickets();
        }
        List<ParkingTicket> lstParkingTicket = new ArrayList<>();
        lstParkingTicket.add((ParkingTicket) parkingTicketService.findById(id));
        return lstParkingTicket.isEmpty() ? List.of() : lstParkingTicket;
    }

    @DeleteMapping("/deleteParkingTicket")
    public String deleteParkingTicket(@RequestParam Long id) throws ParkingTicketNotFoundException {
        return parkingTicketService.deleteById(id);
    }

    @PutMapping("/updateParkingTicket/{id}")
    public ParkingTicket updateParkingTicket(@PathVariable Long id, @RequestBody ParkingTicket parkingTicket) throws ParkingTicketNotFoundException {
        parkingTicket.setParkingTicketId(id);
        return parkingTicketService.updatePanel(parkingTicket);
    }

    @PostMapping("/MappingParkingTicketToSlot")
    public void MappingParkingTicketToSlot(@RequestParam("parkingTicketId") Long parkingTicketId,
                                           @RequestParam("parkingSlotId") Long parkingSlotId) throws ParkingTicketNotFoundException {
        parkingTicketService.addParkingTicketToSlot(parkingTicketId, parkingSlotId);
    }

    @PostMapping("/MappingParkingTicketToExitPanel")
    public void MappingParkingTicketToExitPanel(@RequestParam("parkingTicketId") Long parkingTicketId,
                                                @RequestParam("exitPanelId") Long exitPanelId) throws ParkingTicketNotFoundException {
        parkingTicketService.addParkingTicketToExitPanel(parkingTicketId, exitPanelId);
    }

    @PostMapping("/MappingParkingTicketToEntryPanel")
    public void MappingParkingTicketToEntryPanel(@RequestParam("parkingTicketId") Long parkingTicketId,
                                                 @RequestParam("entryPanelId") Long entryPanelId) throws ParkingTicketNotFoundException {
        parkingTicketService.addParkingTicketToEntryPanel(parkingTicketId, entryPanelId);
    }

    @PostMapping("/MappingParkingTicketToVehicle")
    public void MappingParkingTicketToVehicle(@RequestParam("parkingTicketId") Long parkingTicketId,
                                              @RequestBody Vehicle vehicle) throws ParkingTicketNotFoundException {
        parkingTicketService.addParkingTicketToVehicle(parkingTicketId, vehicle);
    }

    @ExceptionHandler(ParkingTicketNotFoundException.class)
    public ResponseEntity handleParkingTicketNotFoundException(ParkingTicketNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}

