package com.uknowme.services.elevator.goal;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Person;
import com.uknowme.services.elevator.destination.ElevatorDestinationService;
import com.uknowme.services.elevator.distance.ElevatorDistanceService;
import com.uknowme.services.elevator.state.ElevatorStateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ElevatorGoalServiceImpl implements ElevatorGoalService {

    private final ElevatorDestinationService destinationService;
    private final ElevatorDistanceService distanceService;
    private final ElevatorStateService stateService;

    @Override
    public void tryToServe(List<Elevator> elevators, Person person) {
        List<Elevator> noDestination = destinationService.findElevatorWithNoDestinations(elevators);
        noDestination = stateService.stoppedOrClosing(noDestination);

        List<Elevator> personOnTheWayMovingSameDirection = destinationService.onWayToDestinationMovingSameDirection(
                elevators,
                person.getCurrentFloorNumber(),
                person.getDirection()
        );
        personOnTheWayMovingSameDirection = destinationService.lessThanMaxDestinations(personOnTheWayMovingSameDirection);
        personOnTheWayMovingSameDirection = stateService.ifSameFloorClosedOrOpening(personOnTheWayMovingSameDirection, person.getCurrentFloorNumber());

        chooseBestElevator(noDestination, personOnTheWayMovingSameDirection, person.getCurrentFloorNumber())
                .ifPresent(
                        elevator -> {
                            destinationService.addElevatorDestinationToGrabPerson(elevator, person.getCurrentFloorNumber());
                            person.setNeedService(false);
                        }
                );
    }

    private Optional<Elevator> chooseBestElevator(List<Elevator> noDestination, List<Elevator> personOnTheWayMovingSameDirection, int personFloorNumber) {
        if (noDestination.isEmpty() && personOnTheWayMovingSameDirection.isEmpty())
            return Optional.empty();

        List<Elevator> canServe = new ArrayList<>();
        canServe.addAll(noDestination);
        canServe.addAll(personOnTheWayMovingSameDirection);
        canServe = distanceService.canServeByDistance(canServe, personFloorNumber);

        return Optional.of(canServe.get(0));
    }
}
