package com.vehicle.smartparkinglot.controller;

import com.vehicle.smartparkinglot.Exception.EntryPanelNotFoundException;
import com.vehicle.smartparkinglot.entity.EntryPanel;
import com.vehicle.smartparkinglot.service.EntryPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EntryPanelController {

    @Autowired
    private EntryPanelService entryPanelService;

    @PostMapping("/createEntryPanels")
    public EntryPanel createEntryPanel(@RequestBody EntryPanel entryPanel) {
        return entryPanelService.CreateEntryPanel(entryPanel);
    }

    @GetMapping("/getEntryPanels")
    public Object getEntryPanels(@RequestParam(required = false) Long id) throws EntryPanelNotFoundException {
        if(id == null) {
            return entryPanelService.getAllEntryPanels();
        }
        Optional<EntryPanel> entryPanels = entryPanelService.findById(id);
        return entryPanels.isEmpty() ? List.of() : entryPanels;
    }

    @DeleteMapping("/deleteEntryPanel/{id}")
    public String deleteEntryPanel(@PathVariable Long id) throws EntryPanelNotFoundException {
        return entryPanelService.deleteById(id);
    }

    @PutMapping("/updateEntryPanel/{id}")
    public EntryPanel updateEntryPanel(@PathVariable Long id, @RequestBody EntryPanel entryPanel) throws EntryPanelNotFoundException {
        entryPanel.setEntryPanelId(id);
        return entryPanelService.updatePanel(entryPanel);
    }

    @ExceptionHandler(EntryPanelNotFoundException.class)
    public ResponseEntity handleEntryPanelNotFoundException(EntryPanelNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
