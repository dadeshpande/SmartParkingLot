package com.vehicle.smartparkinglot.controller;

import com.vehicle.smartparkinglot.Exception.EntryPanelNotFoundException;
import com.vehicle.smartparkinglot.Exception.ExitPanelNotFoundException;
import com.vehicle.smartparkinglot.entity.EntryPanel;
import com.vehicle.smartparkinglot.entity.ExitPanel;
import com.vehicle.smartparkinglot.service.ExitPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ExitPanelController {

    @Autowired
    private ExitPanelService exitPanelService;

    @PostMapping("/createExitPanels")
    public ExitPanel createExitPanel(@RequestBody ExitPanel ExitPanel) {
        return exitPanelService.CreateExitPanel(ExitPanel);
    }

    @GetMapping("/getExitPanels")
    public List<ExitPanel> getExitPanels(@RequestParam(required = false) Long id) throws ExitPanelNotFoundException {
        if(id == null) {
            return exitPanelService.getAllEntryPanels();
        }
        List<ExitPanel> exitPanels = new ArrayList<>();
        exitPanels.add(exitPanelService.findById(id));
        return exitPanels;
    }

    @DeleteMapping("/deleteExitPanel/{id}")
    public String deleteExitPanel(@PathVariable Long id) throws ExitPanelNotFoundException {
        return exitPanelService.deleteById(id);
    }

    @PutMapping("/updateExitPanel/{id}")
    public ExitPanel updateExitPanel(@PathVariable Long id, @RequestBody ExitPanel ExitPanel) throws ExitPanelNotFoundException {
        ExitPanel.setExitPanelId(id);
        return exitPanelService.updatePanel(ExitPanel);
    }

    @ExceptionHandler(ExitPanelNotFoundException.class)
    public ResponseEntity handleExitPanelNotFoundException(ExitPanelNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
