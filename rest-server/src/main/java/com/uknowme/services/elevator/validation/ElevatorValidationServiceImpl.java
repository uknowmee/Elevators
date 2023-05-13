package com.uknowme.services.elevator.validation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ElevatorValidationServiceImpl implements ElevatorValidationService{
    @Override
    public void validateNumberOfElevators(int numOfElevators) throws ElevatorValidationServiceException {
        if (numOfElevators < 3 || numOfElevators > 16)
            throw new ElevatorValidationServiceException(
                    ElevatorValidationServiceErrorCode.INVALID_NUMBER_OF_ELEVATORS,
                    HttpStatus.BAD_REQUEST,
                    ElevatorValidationServiceException.INVALID_NUMBER_OF_ELEVATORS_MESSAGE
            );
    }
}
