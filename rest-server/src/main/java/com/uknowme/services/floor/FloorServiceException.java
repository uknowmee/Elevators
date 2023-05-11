package com.uknowme.services.floor;

import com.uknowme.controllers.CoreException;
import org.springframework.http.HttpStatus;

public class FloorServiceException extends CoreException {
    public static final String INVALID_NUMBER_OF_FLOORS_MESSAGE = "Invalid number of floors";
    private final FloorServiceErrorCode errorCode;

    public FloorServiceException(FloorServiceErrorCode code, HttpStatus httpStatus, String message) {
        super(message, httpStatus);
        this.errorCode = code;
    }

    public FloorServiceErrorCode getErrorCode() {
        return errorCode;
    }
}
