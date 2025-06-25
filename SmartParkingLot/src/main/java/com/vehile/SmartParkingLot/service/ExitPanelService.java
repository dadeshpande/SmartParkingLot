package com.vehicle.smartparkinglot.service;

import com.vehicle.smartparkinglot.Exception.ExitPanelNotFoundException;
import com.vehicle.smartparkinglot.entity.ExitPanel;
import com.vehicle.smartparkinglot.repository.ExitPanelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExitPanelService {

    @Autowired
    private ExitPanelRepo ExitPanelRepo;

    public ExitPanel CreateExitPanel(ExitPanel ExitPanel) {
        return ExitPanelRepo.save(ExitPanel);
    }


    public ExitPanel findById(Long id) throws ExitPanelNotFoundException {
        Optional<ExitPanel> optionalExitPanel = ExitPanelRepo.findById(id);
        if (optionalExitPanel.isEmpty()) {
            throw new ExitPanelNotFoundException("Exit Panel not found with id: " + id);
        }
        return optionalExitPanel.get();
    }

    public String deleteById(Long id) throws ExitPanelNotFoundException {
        if (id == null || !ExitPanelRepo.existsById(id)) {
            throw new ExitPanelNotFoundException("Exit Panel not found with id: " + id);
        }
        ExitPanelRepo.deleteById(id);
        return "Deleted successfully";
    }

    public ExitPanel updatePanel(ExitPanel ExitPanel) throws ExitPanelNotFoundException {
        if (ExitPanel.getExitPanelId() == null || !ExitPanelRepo.existsById(ExitPanel.getExitPanelId())) {
            throw new ExitPanelNotFoundException("Exit Panel not found with id: " + ExitPanel.getExitPanelId());
        }
        return ExitPanelRepo.save(ExitPanel);
    }

    public List<ExitPanel> getAllEntryPanels() throws ExitPanelNotFoundException {
        List<ExitPanel> exitPanels = ExitPanelRepo.findAll();
        if (exitPanels.isEmpty()) {
            throw new ExitPanelNotFoundException("No Exit Panels found");
        }
        return exitPanels;
    }
}