package com.uknowme.services.elevator.destination;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Direction;

import java.util.List;

public interface ElevatorDestinationService {
    void addElevatorDestinationToGrabPerson(Elevator elevator, int floorNumber);

    int getNextDestination(Elevator elevator);

    void removeReachedDestinations(Elevator elevator);

    boolean isAnyDestinationReached(Elevator elevator);

    void addElevatorDestinationToDeliverPerson(Elevator elevator, int desiredFloorNumber);

    boolean isAnyDestinationReachedNextStep(Elevator elevator);

    List<Elevator> findElevatorWithNoDestinations(List<Elevator> elevators);

    List<Elevator> onWayToDestinationMovingSameDirection(
            List<Elevator> elevators,
            int personFloorNumber,
            Direction personDirection
    );

    List<Elevator> lessThanMaxDestinations(List<Elevator> personOnTheWayMovingSameDirection);
}
