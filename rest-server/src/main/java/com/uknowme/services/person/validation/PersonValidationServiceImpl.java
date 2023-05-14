package com.uknowme.services.person.validation;

import com.uknowme.domain.Building;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PersonValidationServiceImpl implements PersonValidationService{

    private static final int START_FLOOR_NUMBER = 0;

    @Override
    public void checkDifferent(int startFloorNumber, int desiredFloorNumber) {
        if (startFloorNumber == desiredFloorNumber)
            throw new PersonValidationServiceException(
                    PersonValidationServiceErrorCode.START_AND_DESIRED_FLOOR_NUMBER_ARE_SAME,
                    HttpStatus.BAD_REQUEST,
                    PersonValidationServiceException.START_AND_DESIRED_FLOOR_NUMBER_ARE_SAME_MESSAGE
            );
    }

    @Override
    public void validateStartFloorNumber(int startFloorNumber, Building building) throws PersonValidationServiceException {
        if (startFloorNumber < START_FLOOR_NUMBER || startFloorNumber > building.getNumOfFloors())
            throw new PersonValidationServiceException(
                    PersonValidationServiceErrorCode.INVALID_START_FLOOR_NUMBER,
                    HttpStatus.BAD_REQUEST,
                    PersonValidationServiceException.INVALID_START_FLOOR_NUMBER_MESSAGE
            );
    }

    @Override
    public void validateDesiredFloorNumber(int desiredFloorNumber, Building building) throws PersonValidationServiceException {
        if (desiredFloorNumber < START_FLOOR_NUMBER || desiredFloorNumber > building.getNumOfFloors())
            throw new PersonValidationServiceException(
                    PersonValidationServiceErrorCode.INVALID_DESIRED_FLOOR_NUMBER,
                    HttpStatus.BAD_REQUEST,
                    PersonValidationServiceException.INVALID_DESIRED_FLOOR_NUMBER_MESSAGE
            );
    }
}
