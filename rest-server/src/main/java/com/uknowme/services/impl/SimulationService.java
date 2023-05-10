package com.uknowme.services.impl;

import com.uknowme.domain.Building;
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
}
