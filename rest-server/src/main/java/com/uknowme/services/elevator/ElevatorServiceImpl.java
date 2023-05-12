package com.uknowme.services.elevator;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.elevator.ElevatorState;
import com.uknowme.domain.person.Direction;
import com.uknowme.domain.person.Person;
import com.uknowme.services.elevatorDestination.ElevatorDestinationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class ElevatorServiceImpl implements ElevatorService {

    private final ElevatorDestinationService elevatorDestinationService;
    private static final int MAX_DESTINATIONS = 3;

    @Override
    public List<Elevator> createElevators(int numOfElevators) {

        return IntStream
                .range(0, numOfElevators)
                .mapToObj(this::createElevator)
                .collect(Collectors.toList());
    }

    @Override
    public void validateNumberOfElevators(int numOfElevators) throws ElevatorServiceException {
        if (numOfElevators < 3 || numOfElevators > 16)
            throw new ElevatorServiceException(
                    ElevatorServiceErrorCode.INVALID_NUMBER_OF_ELEVATORS,
                    HttpStatus.BAD_REQUEST,
                    ElevatorServiceException.INVALID_NUMBER_OF_ELEVATORS_MESSAGE
            );
    }

    @Override
    public void getPeopleOffElevators(List<Elevator> elevators) {
        List<Elevator> possibleExit = elevators.stream().filter(elevator -> elevator.getState() == ElevatorState.ENTERING_EXITING).toList();
        possibleExit.forEach(this::kickPeopleOffElevator);
    }

    private void kickPeopleOffElevator(Elevator elevator) {
        List<Person> peopleToGetOff = elevator.getPeople().stream().filter(person -> person.getDesiredFloorNumber() == elevator.getCurrentFloor()).toList();

        elevator.getPeople().removeAll(peopleToGetOff);
        peopleToGetOff.forEach(person -> person.setElevator(null));
    }

    @Override
    public void changeStateOfElevators(List<Elevator> elevators) {
        elevators.forEach(elevator -> {
            switch (elevator.getState()) {
                case STOPPED -> checkNextStateFromStopped(elevator);
                case MOVING -> checkNextStateFromMoving(elevator);
                case OPENING -> checkNextStateFromOpening(elevator);
                case CLOSING -> checkNextStateFromClosing(elevator);
                case ENTERING_EXITING -> checkNextStateFromEnteringExiting(elevator);
            }
        });
    }

    private void checkNextStateFromClosing(Elevator elevator) {
        elevator.setState(ElevatorState.STOPPED);
    }

    private void checkNextStateFromEnteringExiting(Elevator elevator) {
        elevator.setOpened(false);
        elevator.setState(ElevatorState.CLOSING);
    }

    private void checkNextStateFromOpening(Elevator elevator) {
        elevator.setOpened(true);
        elevator.setState(ElevatorState.ENTERING_EXITING);
    }

    private void checkNextStateFromStopped(Elevator elevator) {
        if (elevator.getElevatorDestinations().isEmpty()) return;

        elevator.setDestinationFloor(elevatorDestinationService.getNextDestination(elevator));

        if (elevator.getCurrentFloor() == elevator.getDestinationFloor()) {
            elevator.setState(ElevatorState.OPENING);
            elevatorDestinationService.removeReachedDestinations(elevator);
            return;
        }

        elevator.setState(ElevatorState.MOVING);
    }

    private void checkNextStateFromMoving(Elevator elevator) {
        if (elevatorDestinationService.isAnyDestinationReached(elevator)) {
            elevator.setState(ElevatorState.STOPPED);
            return;
        }

        if (elevatorDestinationService.isAnyDestinationReachedNextStep(elevator)) {
            moveElevator(elevator);
            elevator.setState(ElevatorState.STOPPED);
        }
    }

    private void moveElevator(Elevator elevator) {
        if (elevator.getState() != ElevatorState.MOVING) return;
        Direction direction = elevator.getCurrentFloor() < elevator.getDestinationFloor() ? Direction.UP : Direction.DOWN;
        elevator.setCurrentFloor(direction == Direction.UP ? elevator.getCurrentFloor() + 1 : elevator.getCurrentFloor() - 1);
    }

    @Override
    public void moveElevators(List<Elevator> elevators) {
        elevators.forEach(this::moveElevator);
    }

    @Override
    public List<Elevator> getElevatorsReadyForEntering(List<Elevator> elevators) {
        return elevators.stream().filter(elevator -> elevator.getState() == ElevatorState.ENTERING_EXITING).toList();
    }

    @Override
    public void addElevatorGoals(List<Elevator> elevators, List<Person> needsService) {
        needsService.forEach(person -> {
            List<Elevator> canServe = canElevatorServePersonDirection(
                    elevators,
                    person.getDirection(),
                    person.getCurrentFloorNumber()
            );

            if (canServe.isEmpty()) {
                canServe = findElevatorWithNoDestinations(elevators);
                if (canServe.isEmpty()) return;
            }

            canServe = lessThanMaxDestinations(canServe);
            if (canServe.isEmpty()) return;

            canServe = canServeByDistance(canServe, person.getCurrentFloorNumber());

            canServe = ifMovingOrClosingNotSameFloor(canServe, person.getCurrentFloorNumber());
            if (canServe.isEmpty()) return;

            addElevatorDestination(canServe.get(0), person);
            person.setNeedService(false);
        });
    }

    private List<Elevator> findElevatorWithNoDestinations(List<Elevator> elevators) {
        return elevators.stream().filter(elevator -> elevator.getElevatorDestinations().isEmpty()).toList();
    }

    private List<Elevator> lessThanMaxDestinations(List<Elevator> canServe) {
        return canServe.stream().filter(elevator -> elevator.getElevatorDestinations().size() < MAX_DESTINATIONS).toList();
    }

    private void addElevatorDestination(Elevator elevator, Person person) {
        elevatorDestinationService.addElevatorDestinationToGrabPerson(elevator, person.getCurrentFloorNumber());
    }

    private List<Elevator> ifMovingOrClosingNotSameFloor(List<Elevator> canServe, int currentFloorNumber) {
        return canServe.stream().filter(elevator -> {
            if (elevator.getState() == ElevatorState.MOVING || elevator.getState() == ElevatorState.CLOSING) {
                return elevator.getDestinationFloor() != currentFloorNumber;
            }
            return true;
        }).toList();
    }

    private List<Elevator> canServeByDistance(List<Elevator> canServe, int currentFloorNumber) {
        return canServe.stream().sorted((elevator1, elevator2) -> {
            int distance1 = Math.abs(elevator1.getCurrentFloor() - currentFloorNumber);
            int distance2 = Math.abs(elevator2.getCurrentFloor() - currentFloorNumber);
            return Integer.compare(distance1, distance2);
        }).toList();
    }

    private List<Elevator> canElevatorServePersonDirection(List<Elevator> elevators, Direction direction, int callFloorNumber) {
        if (direction == Direction.UP) {
            return canElevatorServeUp(
                    elevators.stream().filter(elevator -> elevator.getCurrentFloor() <= elevator.getDestinationFloor()).toList(),
                    callFloorNumber
            );
        } else {
            return canElevatorServeDown(
                    elevators.stream().filter(elevator -> elevator.getCurrentFloor() >= elevator.getDestinationFloor()).toList(),
                    callFloorNumber
            );
        }
    }

    private List<Elevator> canElevatorServeUp(List<Elevator> stopOrMovingUp, int callFloorNumber) {
        return stopOrMovingUp.stream().filter(elevator -> elevator.getCurrentFloor() <= callFloorNumber).toList();
    }

    private List<Elevator> canElevatorServeDown(List<Elevator> stopOrMovingDown, int callFloorNumber) {
        return stopOrMovingDown.stream().filter(elevator -> elevator.getCurrentFloor() >= callFloorNumber).toList();
    }

    private Elevator createElevator(int serialNumber) {
        Elevator elevator = new Elevator();
        elevator.setState(ElevatorState.STOPPED);
        elevator.setElevatorDestinations(new ArrayList<>());
        elevator.setPeople(new ArrayList<>());

        elevator.setCurrentFloor(0);
        elevator.setDestinationFloor(0);
        elevator.setOpened(false);
        elevator.setSerialNumber(serialNumber);

        return elevator;
    }
}
