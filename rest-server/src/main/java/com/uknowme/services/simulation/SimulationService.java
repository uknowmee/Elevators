package com.uknowme.services.simulation;

import com.uknowme.domain.Simulation;

public interface SimulationService {
    public Simulation createSaveSimulation(int numOfFloors, int numOfElevators);

    public void stopSimulation(int buildingId) throws SimulationServiceException;

    public Simulation getSimulation(int buildingId) throws SimulationServiceException;

    public Simulation makeSimulationStep(int buildingId);
}
