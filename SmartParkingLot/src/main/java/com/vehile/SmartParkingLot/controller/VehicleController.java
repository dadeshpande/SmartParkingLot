package com.vehicle.smartparkinglot.controller;

import com.vehicle.smartparkinglot.Exception.VehicleNotFoundException;
import com.vehicle.smartparkinglot.entity.Vehicle;
import com.vehicle.smartparkinglot.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ModuleDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/createVehicles")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @GetMapping("/getVehicles")
    public List<Vehicle> getVehicles(@RequestParam(required = false) Long id) throws VehicleNotFoundException {
        if(id == null) {
            return vehicleService.getAllVehicles();
        }
        List<Vehicle> lstVehicle = new ArrayList<>();
        lstVehicle.add((Vehicle) vehicleService.findById(id));
        return lstVehicle.isEmpty() ? List.of() : lstVehicle;
    }

    @DeleteMapping("/deleteVehicle")
    public String deleteVehicle(@RequestParam Long id) throws VehicleNotFoundException {
        return vehicleService.deleteById(id);
    }

    @PutMapping("/updateVehicle/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) throws VehicleNotFoundException {
        vehicle.setVehicleId(id);
        return vehicleService.updatePanel(vehicle);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity handleVehicleNotFoundException(VehicleNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
