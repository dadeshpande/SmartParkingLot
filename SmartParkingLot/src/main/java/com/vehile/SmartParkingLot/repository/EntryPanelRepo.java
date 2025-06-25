package com.vehicle.smartparkinglot.repository;

import com.vehicle.smartparkinglot.entity.EntryPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryPanelRepo extends JpaRepository<EntryPanel, Long> {

    // Additional query methods can be defined here if needed
}
