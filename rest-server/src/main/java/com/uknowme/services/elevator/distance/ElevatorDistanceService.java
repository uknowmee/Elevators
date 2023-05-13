package com.uknowme.services.elevator.distance;

import com.uknowme.domain.elevator.Elevator;

import java.util.List;

public interface ElevatorDistanceService {
    List<Elevator> canServeByDistance(List<Elevator> canServe, int currentFloorNumber);
}
