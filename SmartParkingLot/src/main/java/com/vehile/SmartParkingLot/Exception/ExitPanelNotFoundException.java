package com.vehicle.smartparkinglot.Exception;


public class ExitPanelNotFoundException extends Exception {

    public ExitPanelNotFoundException(String message) {
        super(message);
    }

    public ExitPanelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
