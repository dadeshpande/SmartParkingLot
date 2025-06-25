package com.vehicle.smartparkinglot.service;


import com.vehicle.smartparkinglot.Exception.VehicleNotFoundException;
import com.vehicle.smartparkinglot.entity.Vehicle;
import com.vehicle.smartparkinglot.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    public Object findById(Long id) throws VehicleNotFoundException {
        Optional<Vehicle> vehicle = vehicleRepo.findById(id);
        if (vehicle.isEmpty()) {
            throw new VehicleNotFoundException("Exit Panel not found with id: " + id);
        }
        return vehicle.get();
    }


    public String deleteById(Long id) throws VehicleNotFoundException {
        if (id == null || !vehicleRepo.existsById(id)) {
            throw new VehicleNotFoundException("Exit Panel not found with id: " + id);
        }
        vehicleRepo.deleteById(id);
        return "Deleted successfully";
    }

    public Vehicle updatePanel(Vehicle Vehicle) throws VehicleNotFoundException {
        if (Vehicle.getVehicleId() == null || !vehicleRepo.existsById(Vehicle.getVehicleId())) {
            throw new VehicleNotFoundException("Exit Panel not found with id: " + Vehicle.getVehicleId());
        }
        return vehicleRepo.save(Vehicle);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }
}