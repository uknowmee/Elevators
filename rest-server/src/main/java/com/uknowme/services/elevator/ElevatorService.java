package com.uknowme.services.elevator;

import com.uknowme.domain.elevator.Elevator;

import java.util.List;

public interface ElevatorService {

    List<Elevator> createElevators(int numOfElevators);

    void validateNumberOfElevators(int numOfElevators) throws ElevatorServiceException;
}
