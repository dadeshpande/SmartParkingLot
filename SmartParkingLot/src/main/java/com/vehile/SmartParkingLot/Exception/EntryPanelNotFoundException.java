package com.vehicle.smartparkinglot.Exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class EntryPanelNotFoundException extends Exception {

    public EntryPanelNotFoundException(String message) {
        super(message);
    }

    public EntryPanelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
