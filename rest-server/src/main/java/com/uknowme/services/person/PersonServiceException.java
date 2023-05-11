package com.uknowme.services.person;

import com.uknowme.controllers.CoreException;
import org.springframework.http.HttpStatus;

public class PersonServiceException extends CoreException {
    public static final String START_AND_DESIRED_FLOOR_NUMBER_ARE_SAME_MESSAGE = "Start and desired floor number are same";
    public static final String INVALID_START_FLOOR_NUMBER_MESSAGE = "Invalid start floor number";
    public static final String INVALID_DESIRED_FLOOR_NUMBER_MESSAGE = "Invalid desired floor number";
    private final PersonServiceErrorCode errorCode;

    public PersonServiceException(PersonServiceErrorCode code, HttpStatus httpStatus, String message) {
        super(message, httpStatus);
        this.errorCode = code;
    }

    public PersonServiceErrorCode getErrorCode() {
        return errorCode;
    }
}
