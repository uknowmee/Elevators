package com.uknowme.services.elevator.validation;

import com.uknowme.controllers.CoreException;
import org.springframework.http.HttpStatus;

public class ElevatorValidationServiceException extends CoreException {
    public static final String INVALID_NUMBER_OF_ELEVATORS_MESSAGE = "Invalid number of elevators";
    private final ElevatorValidationServiceErrorCode errorCode;

    public ElevatorValidationServiceException(ElevatorValidationServiceErrorCode code, HttpStatus httpStatus, String message) {
        super(message, httpStatus);
        this.errorCode = code;
    }

    public ElevatorValidationServiceErrorCode getErrorCode() {
        return errorCode;
    }
}
