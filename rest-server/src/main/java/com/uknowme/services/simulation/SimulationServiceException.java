package com.uknowme.services.simulation;

import com.uknowme.controllers.CoreException;
import org.springframework.http.HttpStatus;

public class SimulationServiceException extends CoreException {

    public static final String SIMULATION_NOT_FOUND_MESSAGE = "Simulation not found";
    public static final String SIMULATION_ALREADY_STOPPED_MESSAGE = "Simulation has already been stopped";

    private final SimulationServiceErrorCode errorCode;

    public SimulationServiceException(SimulationServiceErrorCode code, HttpStatus httpStatus, String message) {
        super(message, httpStatus);
        this.errorCode = code;
    }

    public SimulationServiceErrorCode getErrorCode() {
        return errorCode;
    }
}

