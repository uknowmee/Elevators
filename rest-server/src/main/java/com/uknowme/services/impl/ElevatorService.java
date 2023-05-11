package com.uknowme.services.impl;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.services.IElevatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ElevatorService implements IElevatorService {
    @Override
    public List<Elevator> getBuildingElevators(int buildingId) {
        return null;
    }
}
