package com.uknowme.services.elevator.destination;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.elevator.ElevatorDestination;
import com.uknowme.domain.person.Direction;
import com.uknowme.services.elevator.direction.ElevatorDirectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class ElevatorDestinationServiceImpl implements ElevatorDestinationService {

    private static final int MAX_DESTINATIONS = 3;
    private final ElevatorDirectionService directionService;

    @Override
    public void addElevatorDestinationToGrabPerson(Elevator elevator, int currentFloorNumber) {
        ElevatorDestination elevatorDestination = createElevatorDestination(elevator, currentFloorNumber);
        elevator.getElevatorDestinations().add(elevatorDestination);
        updateElevatorDestinationFloor(elevator, elevatorDestination);
    }

    @Override
    public int getNextDestination(Elevator elevator) {
        if (elevator.getElevatorDestinations().size() == 0) return elevator.getCurrentFloor();

        if (elevator.getElevatorDestinations().size() > 1) {
            elevator.getElevatorDestinations().sort(Comparator.comparing(ElevatorDestination::getInitialTime));
        }

        ElevatorDestination newElevatorDestination = elevator.getElevatorDestinations().get(0);

        if (elevator.getElevatorDestinations().size() < 2) return newElevatorDestination.getFloorNumber();

        return checkStopBetweenFloors(elevator, newElevatorDestination);
    }

    @Override
    public void removeReachedDestinations(Elevator elevator) {
        elevator.getElevatorDestinations().forEach(destination -> {
            if (destination.getFloorNumber() == elevator.getCurrentFloor()) {
                destination.setElevator(null);
            }
        });
        elevator.getElevatorDestinations().removeIf(elevatorDestination -> elevatorDestination.getFloorNumber() == elevator.getCurrentFloor());
    }

    @Override
    public boolean isAnyDestinationReached(Elevator elevator) {
        return elevator
                .getElevatorDestinations()
                .stream()
                .anyMatch(elevatorDestination -> elevatorDestination.getFloorNumber() == elevator.getCurrentFloor());
    }

    @Override
    public void addElevatorDestinationToDeliverPerson(Elevator elevator, int desiredFloorNumber) {
        ElevatorDestination elevatorDestination = createElevatorDestination(elevator, desiredFloorNumber);
        elevator.getElevatorDestinations().add(elevatorDestination);
    }

    @Override
    public boolean isAnyDestinationReachedNextStep(Elevator elevator) {
        Direction direction = elevator.getCurrentFloor() < elevator.getDestinationFloor() ? Direction.UP : Direction.DOWN;
        int nextFloor = direction == Direction.UP ? elevator.getCurrentFloor() + 1 : elevator.getCurrentFloor() - 1;
        return elevator
                .getElevatorDestinations()
                .stream()
                .anyMatch(elevatorDestination -> elevatorDestination.getFloorNumber() == nextFloor);
    }

    @Override
    public List<Elevator> findElevatorWithNoDestinations(List<Elevator> elevators) {
        return elevators.stream().filter(elevator -> elevator.getElevatorDestinations().isEmpty()).toList();
    }

    @Override
    public List<Elevator> onWayToDestinationMovingSameDirection(
            List<Elevator> elevators,
            int personFloorNumber,
            Direction personDirection
    ) {
        return elevators.stream().filter(elevator -> directionService.getElevatorDirection(elevator) == personDirection
                        && elevator.getCurrentFloor() < personFloorNumber
                        && elevator.getDestinationFloor() > personFloorNumber
                )
                .toList();
    }

    @Override
    public List<Elevator> lessThanMaxDestinations(List<Elevator> canServe) {
        return canServe.stream().filter(elevator -> elevator.getElevatorDestinations().size() < MAX_DESTINATIONS).toList();
    }

    private int checkStopBetweenFloors(Elevator elevator, ElevatorDestination newElevatorDestination) {
        Direction direction = elevator.getDestinationFloor() > elevator.getCurrentFloor()
                ? Direction.UP
                : Direction.DOWN;

        for (ElevatorDestination destination : elevator.getElevatorDestinations()) {
            if (direction == Direction.UP
                    && destination.getFloorNumber() >= elevator.getCurrentFloor()
                    && destination.getFloorNumber() < newElevatorDestination.getFloorNumber()
            ) {
                newElevatorDestination = destination;
            } else if (direction == Direction.DOWN
                    && destination.getFloorNumber() <= elevator.getCurrentFloor()
                    && destination.getFloorNumber() > newElevatorDestination.getFloorNumber()
            ) {
                newElevatorDestination = destination;
            }
        }

        return newElevatorDestination.getFloorNumber();
    }

    private void updateElevatorDestinationFloor(Elevator elevator, ElevatorDestination elevatorDestination) {
        if (elevator.getCurrentFloor() == elevator.getDestinationFloor()) {
            elevator.setDestinationFloor(elevatorDestination.getFloorNumber());
        }
    }

    private ElevatorDestination createElevatorDestination(Elevator elevator, int floorNumber) {
        ElevatorDestination elevatorDestination = new ElevatorDestination();
        elevatorDestination.setInitialTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        elevatorDestination.setFloorNumber(floorNumber);
        elevatorDestination.setElevator(elevator);

        return elevatorDestination;
    }
}
