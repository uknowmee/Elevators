package com.uknowme.services.simulation;

import com.uknowme.domain.Building;
import com.uknowme.domain.Simulation;
import com.uknowme.repositories.SimulationRepository;
import com.uknowme.services.building.BuildingService;
import com.uknowme.services.elevator.ElevatorService;
import com.uknowme.services.floor.FloorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@AllArgsConstructor
@Service
public class SimulationServiceImpl implements SimulationService {

    private final SimulationRepository simulationRepository;
    private final BuildingService buildingService;
    private final FloorService floorService;
    private final ElevatorService elevatorService;

    @Override
    public Simulation createSaveSimulation(int numOfFloors, int numOfElevators) {

        floorService.validateNumberOfFloors(numOfFloors);
        elevatorService.validateNumberOfElevators(numOfElevators);

        Building building = buildingService.createBuildingWithFloorsAndElevators(numOfFloors, numOfElevators);
        Simulation simulation = createSimulation();
        simulation.setBuilding(building);

        return simulationRepository.save(simulation);
    }

    @Override
    public void stopSimulation(int buildingId) throws SimulationServiceException {
        Simulation simulation = getSimulation(buildingId);

        if (simulation.getEndTime() != null)
            throw new SimulationServiceException(
                    SimulationServiceErrorCode.SIMULATION_ALREADY_STOPPED,
                    HttpStatus.CONFLICT,
                    SimulationServiceException.SIMULATION_ALREADY_STOPPED_MESSAGE
            );

        simulation.setEndTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        simulationRepository.save(simulation);
    }

    @Override
    public Simulation getSimulation(int buildingId) throws SimulationServiceException {
        return simulationRepository.findSimulationByBuildingId(buildingId)
                .orElseThrow(() -> new SimulationServiceException(
                        SimulationServiceErrorCode.SIMULATION_NOT_FOUND,
                        HttpStatus.NOT_FOUND,
                        SimulationServiceException.SIMULATION_NOT_FOUND_MESSAGE
                ));
    }

    @Override
    public Simulation makeSimulationStep(int buildingId) {
        Simulation simulation = getSimulation(buildingId);
//        TODO: implement simulation step
        return null;
    }

    private Simulation createSimulation() {
        Simulation simulation = new Simulation();
        simulation.setStartTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        return simulation;
    }
}
