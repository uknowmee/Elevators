package com.uknowme.services.elevator.state;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.elevator.ElevatorState;

import java.util.List;

public interface ElevatorStateService {
    void checkNextStateFromStopped(Elevator elevator);

    void checkNextStateFromMoving(Elevator elevator);

    void checkNextStateFromOpening(Elevator elevator);

    void checkNextStateFromClosing(Elevator elevator);

    void checkNextStateFromEnteringExiting(Elevator elevator);

    boolean isEnteringExiting(Elevator elevator);

    List<Elevator> stoppedOrClosing(List<Elevator> noDestination);

    List<Elevator> ifSameFloorClosedOrOpening(List<Elevator> personOnTheWayMovingSameDirection, int currentFloorNumber);

    ElevatorState defaultState();
}
