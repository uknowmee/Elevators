package com.uknowme.services;

import com.uknowme.domain.elevator.Elevator;

import java.util.List;

public interface IElevatorService {
    public List<Elevator> getBuildingElevators(int buildingId);
}
