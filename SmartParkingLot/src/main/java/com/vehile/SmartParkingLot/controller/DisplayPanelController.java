package com.vehicle.smartparkinglot.controller;

import com.vehicle.smartparkinglot.Exception.DisplayPanelNotFoundException;
import com.vehicle.smartparkinglot.entity.DisplayPanel;
import com.vehicle.smartparkinglot.service.DisplayPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DisplayPanelController {

    @Autowired
    private DisplayPanelService displayPanelService;

    @PostMapping("/createDisplayPanels")
    public DisplayPanel createDisplayPanel(@RequestBody DisplayPanel DisplayPanel) {
        return displayPanelService.CreateDisplayPanel(DisplayPanel);
    }

    @GetMapping("/getDisplayPanels/{id}")
    public DisplayPanel getDisplayPanels(@PathVariable Long id) throws DisplayPanelNotFoundException {
        return displayPanelService.findById(id);
    }

    @GetMapping("/getAllDisplayPanels")
    public List<DisplayPanel> getAllDisplayPanels() throws DisplayPanelNotFoundException {
        return displayPanelService.findAll();
    }

    @DeleteMapping("/deleteDisplayPanel/{id}")
    public String deleteDisplayPanel(@PathVariable Long id) throws DisplayPanelNotFoundException {
        return displayPanelService.deleteById(id);
    }

    @PutMapping("/updateDisplayPanel/{id}")
    public DisplayPanel updateDisplayPanel(@PathVariable Long id, @RequestBody DisplayPanel DisplayPanel) throws DisplayPanelNotFoundException {
        DisplayPanel.setDisplayPanelId(id);
        return displayPanelService.updatePanel(DisplayPanel);
    }

    @ExceptionHandler(DisplayPanelNotFoundException.class)
    public ResponseEntity<String> handleDisplayPanelNotFoundException(DisplayPanelNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
