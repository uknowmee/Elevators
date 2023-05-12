package com.uknowme.services.elevatorDestination;

import com.uknowme.domain.elevator.Elevator;

public interface ElevatorDestinationService {
    void addElevatorDestinationToGrabPerson(Elevator elevator, int floorNumber);

    int getNextDestination(Elevator elevator);

    void removeReachedDestinations(Elevator elevator);

    boolean isAnyDestinationReached(Elevator elevator);

    void addElevatorDestinationToDeliverPerson(Elevator elevator, int desiredFloorNumber);

    boolean isAnyDestinationReachedNextStep(Elevator elevator);
}
