package com.uknowme.services.elevator;

import com.uknowme.controllers.CoreException;
import org.springframework.http.HttpStatus;

public class ElevatorServiceException extends CoreException {
    public static final String INVALID_NUMBER_OF_ELEVATORS_MESSAGE = "Invalid number of elevators";
    private final ElevatorServiceErrorCode errorCode;

    public ElevatorServiceException(ElevatorServiceErrorCode code, HttpStatus httpStatus, String message) {
        super(message, httpStatus);
        this.errorCode = code;
    }

    public ElevatorServiceErrorCode getErrorCode() {
        return errorCode;
    }
}
