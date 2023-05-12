package com.uknowme.services.simulation;

import com.uknowme.domain.Building;
import com.uknowme.domain.Simulation;
import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Person;
import com.uknowme.repositories.SimulationRepository;
import com.uknowme.services.building.BuildingService;
import com.uknowme.services.elevator.ElevatorService;
import com.uknowme.services.floor.FloorService;
import com.uknowme.services.person.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class SimulationServiceImpl implements SimulationService {

    private final SimulationRepository simulationRepository;
    private final BuildingService buildingService;
    private final FloorService floorService;
    private final ElevatorService elevatorService;
    private final PersonService personService;

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

        List<Person> peopleInBuilding = simulation.getBuilding().getPeople();
        List<Elevator> elevators = simulation.getBuilding().getElevators();
        List<Person> peopleNotInElevatorsButOnFloors = personService.getPeopleNotInElevatorsButOnFloors(peopleInBuilding);

        elevatorService.getPeopleOffElevators(elevators);
        personService.enterElevator(peopleNotInElevatorsButOnFloors, elevatorService.getElevatorsReadyForEntering(elevators));

        List<Person> needsService = personService.getPeopleWhoNeedService(peopleInBuilding);
        elevatorService.addElevatorGoals(elevators, needsService);

        elevatorService.changeStateOfElevators(elevators);
        elevatorService.moveElevators(elevators);

        return simulationRepository.save(simulation);
    }

    private Simulation createSimulation() {
        Simulation simulation = new Simulation();
        simulation.setStartTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        return simulation;
    }
}
