package com.uknowme.services.simulation;

import com.uknowme.domain.Simulation;

public interface SimulationService {
    void stopSimulation(int buildingId) throws SimulationServiceException;

    Simulation createSaveSimulation(int numOfFloors, int numOfElevators);

    Simulation getSimulation(int buildingId) throws SimulationServiceException;

    Simulation makeSimulationStep(int buildingId);
}
