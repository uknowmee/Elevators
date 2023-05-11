package com.uknowme.services;

import com.uknowme.domain.Simulation;

public interface ISimulationService {
    Simulation createSimulation(int numOfFloors, int numOfElevators);

    void stopSimulation(int buildingId);

    Simulation getSimulation(int buildingId);

    Simulation makeSimulationStep(int buildingId);
}
