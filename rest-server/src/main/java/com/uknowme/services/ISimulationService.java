package com.uknowme.services;

import com.uknowme.domain.Simulation;

public interface ISimulationService {
    public Simulation createSimulation(int numOfFloors, int numOfElevators);

    public void stopSimulation(int buildingId);

    public Simulation getSimulation(int buildingId);

    public Simulation makeSimulationStep(int buildingId);
}
