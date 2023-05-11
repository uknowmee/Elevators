package com.uknowme.services.impl;

import com.uknowme.domain.Building;
import com.uknowme.domain.Simulation;
import com.uknowme.repositories.SimulationRepository;
import com.uknowme.services.IBuildingService;
import com.uknowme.services.ISimulationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@AllArgsConstructor
@Service
public class SimulationService implements ISimulationService {

    private final SimulationRepository simulationRepository;
    private final IBuildingService buildingService;

    @Override
    public Simulation createSaveSimulation(int numOfFloors, int numOfElevators) {
        Building building = buildingService.createBuildingWithFloorsAndElevators(numOfFloors, numOfElevators);
        Simulation simulation = createSimulation();
        simulation.setBuilding(building);
        return simulationRepository.save(simulation);
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

    private Simulation createSimulation(){
        Simulation simulation = new Simulation();
        simulation.setStartTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        return simulation;
    }
}
