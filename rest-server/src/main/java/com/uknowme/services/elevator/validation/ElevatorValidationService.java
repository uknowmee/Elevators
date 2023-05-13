package com.uknowme.services.elevator.validation;

public interface ElevatorValidationService {
    void validateNumberOfElevators(int numOfElevators) throws ElevatorValidationServiceException;
}
