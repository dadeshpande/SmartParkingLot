package com.vehicle.smartparkinglot.repository;

import com.vehicle.smartparkinglot.entity.ExitPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExitPanelRepo extends JpaRepository<ExitPanel, Long> {

    // Additional query methods can be defined here if needed
}
