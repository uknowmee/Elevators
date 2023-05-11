package com.uknowme.services.impl;

import com.uknowme.domain.Simulation;
import com.uknowme.services.ISimulationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SimulationService implements ISimulationService {
    @Override
    public Simulation createSimulation(int numOfFloors, int numOfElevators) {
        return null;
    }

    @Override
    public void stopSimulation(int buildingId) {

    }

    @Override
    public Simulation getSimulation(int buildingId) {
        return null;
    }

    @Override
    public Simulation makeSimulationStep(int buildingId) {
        return null;
    }
}
