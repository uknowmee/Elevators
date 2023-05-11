package com.uknowme.services.elevator;

import com.uknowme.domain.elevator.Elevator;

import java.util.List;

public interface IElevatorService {
    public List<Elevator> getBuildingElevators(int buildingId);

    List<Elevator> createElevators(int numOfElevators);
}
