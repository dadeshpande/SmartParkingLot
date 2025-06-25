package com.vehicle.smartparkinglot.service;

import com.vehicle.smartparkinglot.Exception.DisplayPanelNotFoundException;
import com.vehicle.smartparkinglot.entity.DisplayPanel;
import com.vehicle.smartparkinglot.repository.DisplayPanelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisplayPanelService {

    @Autowired
    private DisplayPanelRepo DisplayPanelRepo;

    public DisplayPanel CreateDisplayPanel(DisplayPanel DisplayPanel) {
        return DisplayPanelRepo.save(DisplayPanel);
    }


    public DisplayPanel findById(Long id) throws DisplayPanelNotFoundException {
        Optional<DisplayPanel> optionalDisplayPanel = DisplayPanelRepo.findById(id);
        if (optionalDisplayPanel.isEmpty()) {
            throw new DisplayPanelNotFoundException("Display Panel not found with id: " + id);
        }
        return optionalDisplayPanel.get();
    }

    public String deleteById(Long id) throws DisplayPanelNotFoundException {
        if (id == null || !DisplayPanelRepo.existsById(id)) {
            throw new DisplayPanelNotFoundException("Display Panel not found with id: " + id);
        }
        DisplayPanelRepo.deleteById(id);
        return "Deleted successfully";
    }

    public DisplayPanel updatePanel(DisplayPanel DisplayPanel) throws DisplayPanelNotFoundException {
        if (DisplayPanel.getDisplayPanelId() == null || !DisplayPanelRepo.existsById(DisplayPanel.getDisplayPanelId())) {
            throw new DisplayPanelNotFoundException("Display Panel not found with id: " + DisplayPanel.getDisplayPanelId());
        }
        return DisplayPanelRepo.save(DisplayPanel);
    }

    public List<DisplayPanel> findAll() throws DisplayPanelNotFoundException {
        List<DisplayPanel> displayPanels = DisplayPanelRepo.findAll();
        if (displayPanels.isEmpty()) {
            throw new DisplayPanelNotFoundException("No Display Panels found");
        }
        return displayPanels;
    }
}