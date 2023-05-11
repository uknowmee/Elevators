package com.uknowme.services.building;

import com.uknowme.controllers.CoreException;
import org.springframework.http.HttpStatus;

public class BuildingServiceException extends CoreException {
    public static final String BUILDING_NOT_FOUND_MESSAGE = "Building not found";
    private final BuildingServiceErrorCode errorCode;

    public BuildingServiceException(BuildingServiceErrorCode code, HttpStatus httpStatus, String message) {
        super(message, httpStatus);
        this.errorCode = code;
    }

    public BuildingServiceErrorCode getErrorCode() {
        return errorCode;
    }
}
