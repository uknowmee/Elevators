package com.uknowme.services.elevator.distance;

import com.uknowme.domain.elevator.Elevator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElevatorDistanceServiceImpl implements ElevatorDistanceService {
    @Override
    public List<Elevator> canServeByDistance(List<Elevator> canServe, int currentFloorNumber) {
        return canServe.stream().sorted((elevator1, elevator2) -> {
            int distance1 = Math.abs(elevator1.getCurrentFloor() - currentFloorNumber);
            int distance2 = Math.abs(elevator2.getCurrentFloor() - currentFloorNumber);
            return Integer.compare(distance1, distance2);
        }).toList();
    }
}
