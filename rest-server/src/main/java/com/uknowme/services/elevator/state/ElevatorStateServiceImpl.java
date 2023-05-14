package com.uknowme.services.elevator.state;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.elevator.ElevatorState;
import com.uknowme.services.elevator.destination.ElevatorDestinationService;
import com.uknowme.services.elevator.move.ElevatorMoveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ElevatorStateServiceImpl implements ElevatorStateService {

    private final ElevatorDestinationService destinationService;
    private final ElevatorMoveService moveService;

    public List<Elevator> stoppedOrClosing(List<Elevator> noDestination) {
        return noDestination.stream()
                .filter(elevator -> elevator.getState() == ElevatorState.STOPPED ||
                        elevator.getState() == ElevatorState.CLOSING
                )
                .toList();
    }

    @Override
    public List<Elevator> ifSameFloorClosedOrOpening(List<Elevator> noDestination, int currentFloorNumber) {
        return noDestination.stream()
                .filter(elevator -> elevator.getCurrentFloor() != currentFloorNumber
                        || (elevator.getState() == ElevatorState.STOPPED || elevator.getState() == ElevatorState.OPENING)
                )
                .toList();
    }

    @Override
    public void checkNextStateFromClosing(Elevator elevator) {
        elevator.setState(ElevatorState.STOPPED);
    }

    @Override
    public void checkNextStateFromEnteringExiting(Elevator elevator) {
        elevator.setOpened(false);
        elevator.setState(ElevatorState.CLOSING);
    }

    @Override
    public void checkNextStateFromOpening(Elevator elevator) {
        elevator.setOpened(true);
        elevator.setState(ElevatorState.ENTERING_EXITING);
    }

    @Override
    public void checkNextStateFromStopped(Elevator elevator) {
        if (elevator.getElevatorDestinations().isEmpty()) return;

        elevator.setDestinationFloor(destinationService.getNextDestination(elevator));

        if (elevator.getCurrentFloor() == elevator.getDestinationFloor()) {
            elevator.setState(ElevatorState.OPENING);
            destinationService.removeReachedDestinations(elevator);
            return;
        }

        elevator.setState(ElevatorState.MOVING);
    }

    @Override
    public void checkNextStateFromMoving(Elevator elevator) {
        if (destinationService.isAnyDestinationReached(elevator)) {
            elevator.setState(ElevatorState.STOPPED);
            return;
        }

        if (destinationService.isAnyDestinationReachedNextStep(elevator)) {
            moveService.moveElevator(elevator);
            elevator.setState(ElevatorState.STOPPED);
        }
    }

    @Override
    public boolean isEnteringExiting(Elevator elevator) {
        return elevator.getState() == ElevatorState.ENTERING_EXITING;
    }

    @Override
    public ElevatorState defaultState() {
        return ElevatorState.STOPPED;
    }
}
