package com.uknowme.services.elevatorDestination;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.elevator.ElevatorDestination;
import com.uknowme.domain.person.Direction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;

@AllArgsConstructor
@Service
public class ElevatorDestinationServiceImpl implements ElevatorDestinationService {

    private ElevatorDestination createElevatorDestination(Elevator elevator, int floorNumber) {
        ElevatorDestination elevatorDestination = new ElevatorDestination();
        elevatorDestination.setInitialTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        elevatorDestination.setFloorNumber(floorNumber);
        elevatorDestination.setElevator(elevator);

        return elevatorDestination;
    }

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

        return elevator.getElevatorDestinations().get(0).getFloorNumber();
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

    private void updateElevatorDestinationFloor(Elevator elevator, ElevatorDestination elevatorDestination) {
        if (elevator.getCurrentFloor() == elevator.getDestinationFloor()) {
            elevator.setDestinationFloor(elevatorDestination.getFloorNumber());
        }
    }
}
