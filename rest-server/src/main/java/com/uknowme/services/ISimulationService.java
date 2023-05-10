package com.uknowme.services;

import com.uknowme.domain.Building;
import com.uknowme.domain.Simulation;

public interface ISimulationService {
    public Simulation createSimulation(int numOfFloors, int numOfElevators);
}
