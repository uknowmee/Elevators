package com.uknowme.services.elevatorDestination;

import com.uknowme.controllers.CoreException;
import org.springframework.http.HttpStatus;

public class ElevatorDestinationServiceException extends CoreException {
    private final ElevatorDestinationServiceErrorCode errorCode;

    public ElevatorDestinationServiceException(ElevatorDestinationServiceErrorCode code, HttpStatus httpStatus, String message) {
        super(message, httpStatus);
        this.errorCode = code;
    }

    public ElevatorDestinationServiceErrorCode getErrorCode() {
        return errorCode;
    }
}
