package com.uknowme.services.person.validation;

import com.uknowme.domain.Building;

public interface PersonValidationService {
    void validateStartFloorNumber(int startFloorNumber, Building building);
    void validateDesiredFloorNumber(int desiredFloorNumber, Building building);
    void checkDifferent(int startFloorNumber, int desiredFloorNumber);
}
