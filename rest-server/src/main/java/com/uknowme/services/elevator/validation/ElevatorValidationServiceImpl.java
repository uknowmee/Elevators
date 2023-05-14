package com.uknowme.services.elevator.validation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ElevatorValidationServiceImpl implements ElevatorValidationService {

    private static final int MIN_ELEVATORS = 3;
    private static final int MAX_ELEVATORS = 16;

    @Override
    public void validateNumberOfElevators(int numOfElevators) throws ElevatorValidationServiceException {
        if (numOfElevators < MIN_ELEVATORS || numOfElevators > MAX_ELEVATORS)
            throw new ElevatorValidationServiceException(
                    ElevatorValidationServiceErrorCode.INVALID_NUMBER_OF_ELEVATORS,
                    HttpStatus.BAD_REQUEST,
                    ElevatorValidationServiceException.INVALID_NUMBER_OF_ELEVATORS_MESSAGE
            );
    }
}
