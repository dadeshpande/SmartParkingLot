package com.vehicle.smartparkinglot.service;

import com.vehicle.smartparkinglot.Exception.EntryPanelNotFoundException;
import com.vehicle.smartparkinglot.entity.EntryPanel;
import org.springframework.beans.factory.annotation.Autowired;
import com.vehicle.smartparkinglot.repository.EntryPanelRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryPanelService {

    @Autowired
    private EntryPanelRepo entryPanelRepo;

    public EntryPanel CreateEntryPanel(EntryPanel entryPanel) {
        return entryPanelRepo.save(entryPanel);
    }


    public Optional<EntryPanel> findById(Long id) throws EntryPanelNotFoundException {
        if(id == null) {
            throw new EntryPanelNotFoundException("Entry Panel ID cannot be null");
        }
        Optional<EntryPanel> optionalEntryPanel = entryPanelRepo.findById(id);
        if (optionalEntryPanel.isEmpty()) {
            throw new EntryPanelNotFoundException("Entry Panel not found with id: " + id);
        }
        return entryPanelRepo.findById(id);
    }

    public List<EntryPanel> getAllEntryPanels() {
        return entryPanelRepo.findAll();
    }

    public String deleteById(Long id) throws EntryPanelNotFoundException {
        if (id == null || !entryPanelRepo.existsById(id)) {
            throw new EntryPanelNotFoundException("Entry Panel not found with id: " + id);
        }
        entryPanelRepo.deleteById(id);
        return "Deleted successfully";
    }

    public EntryPanel updatePanel(EntryPanel entryPanel) throws EntryPanelNotFoundException {
        if (entryPanel.getEntryPanelId() == null || !entryPanelRepo.existsById(entryPanel.getEntryPanelId())) {
            throw new EntryPanelNotFoundException("Entry Panel not found with id: " + entryPanel.getEntryPanelId());
        }
        return entryPanelRepo.save(entryPanel);
    }
}
