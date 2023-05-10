package com.uknowme.services;

import com.uknowme.domain.Elevator;

import java.util.List;

public interface IElevatorService {
    public List<Elevator> getBuildingElevators(int buildingId);
}
