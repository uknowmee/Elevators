package com.uknowme.services.elevator;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Person;
import com.uknowme.services.elevator.goal.ElevatorGoalService;
import com.uknowme.services.elevator.move.ElevatorMoveService;
import com.uknowme.services.elevator.state.ElevatorStateService;
import com.uknowme.services.elevator.validation.ElevatorValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class ElevatorServiceImpl implements ElevatorService {

    private final ElevatorValidationService validationService;
    private final ElevatorStateService stateService;
    private final ElevatorMoveService moveService;
    private final ElevatorGoalService goalService;

    @Override
    public List<Elevator> createElevators(int numOfElevators) {
        return IntStream
                .range(0, numOfElevators)
                .mapToObj(this::createElevator)
                .collect(Collectors.toList());
    }

    @Override
    public void validateNumberOfElevators(int numOfElevators) {
        validationService.validateNumberOfElevators(numOfElevators);
    }

    @Override
    public void getPeopleOffElevators(List<Elevator> elevators) {
        List<Elevator> possibleExit = elevators.stream().filter(stateService::isEnteringExiting).toList();
        possibleExit.forEach(this::kickPeopleOffElevator);
    }

    @Override
    public void changeStateOfElevators(List<Elevator> elevators) {
        elevators.forEach(elevator -> {
            switch (elevator.getState()) {
                case STOPPED -> stateService.checkNextStateFromStopped(elevator);
                case MOVING -> stateService.checkNextStateFromMoving(elevator);
                case OPENING -> stateService.checkNextStateFromOpening(elevator);
                case CLOSING -> stateService.checkNextStateFromClosing(elevator);
                case ENTERING_EXITING -> stateService.checkNextStateFromEnteringExiting(elevator);
            }
        });
    }

    @Override
    public void moveElevators(List<Elevator> elevators) {
        elevators.forEach(moveService::moveElevator);
    }

    @Override
    public List<Elevator> getElevatorsReadyForEntering(List<Elevator> elevators) {
        return elevators.stream().filter(stateService::isEnteringExiting).toList();
    }

    @Override
    public void addElevatorGoals(List<Elevator> elevators, List<Person> needsService) {
        for (Person person : needsService) {
            goalService.tryToServe(elevators, person);
        }
    }

    private void kickPeopleOffElevator(Elevator elevator) {
        List<Person> peopleToGetOff = elevator.getPeople().stream()
                .filter(person -> person.getDesiredFloorNumber() == elevator.getCurrentFloor())
                .toList();

        elevator.getPeople().removeAll(peopleToGetOff);
        peopleToGetOff.forEach(person -> person.setElevator(null));
    }

    private Elevator createElevator(int serialNumber) {
        Elevator elevator = new Elevator();
        elevator.setState(stateService.defaultState());
        elevator.setElevatorDestinations(new ArrayList<>());
        elevator.setPeople(new ArrayList<>());

        elevator.setCurrentFloor(0);
        elevator.setDestinationFloor(0);
        elevator.setOpened(false);
        elevator.setSerialNumber(serialNumber);

        return elevator;
    }
}
