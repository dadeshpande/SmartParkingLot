package com.vehicle.smartparkinglot.repository;

import com.vehicle.smartparkinglot.entity.DisplayPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisplayPanelRepo extends JpaRepository<DisplayPanel, Long> {

    // Additional query methods can be defined here if needed
}
