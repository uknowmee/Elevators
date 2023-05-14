package com.uknowme.services.elevator.destination;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Direction;

import java.util.List;

public interface ElevatorDestinationService {
    void addElevatorDestinationToGrabPerson(Elevator elevator, int floorNumber);

    void removeReachedDestinations(Elevator elevator);

    void addElevatorDestinationToDeliverPerson(Elevator elevator, int desiredFloorNumber);

    int getNextDestination(Elevator elevator);

    boolean isAnyDestinationReached(Elevator elevator);

    boolean isAnyDestinationReachedNextStep(Elevator elevator);

    List<Elevator> findElevatorWithNoDestinations(List<Elevator> elevators);

    List<Elevator> onWayToDestinationMovingSameDirection(
            List<Elevator> elevators,
            int personFloorNumber,
            Direction personDirection
    );

    List<Elevator> lessThanMaxDestinations(List<Elevator> personOnTheWayMovingSameDirection);
}
