package com.uknowme.services.elevator.goal;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Person;
import com.uknowme.services.elevator.destination.ElevatorDestinationService;
import com.uknowme.services.elevator.distance.ElevatorDistanceService;
import com.uknowme.services.elevator.state.ElevatorStateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        List<Elevator> personOnTheWayMovingSameDirection = destinationService.onWayToDestinationMovingSameDirection(
                elevators,
                person.getCurrentFloorNumber(),
                person.getDirection()
        );

        personOnTheWayMovingSameDirection = destinationService.lessThanMaxDestinations(personOnTheWayMovingSameDirection);

        noDestination = distanceService.canServeByDistance(noDestination, person.getCurrentFloorNumber());
        personOnTheWayMovingSameDirection = distanceService.canServeByDistance(personOnTheWayMovingSameDirection, person.getCurrentFloorNumber());

        noDestination = stateService.stoppedOrClosing(noDestination);
        personOnTheWayMovingSameDirection = stateService.ifSameFloorClosedOrOpening(personOnTheWayMovingSameDirection, person.getCurrentFloorNumber());

        chooseBestElevator(noDestination, personOnTheWayMovingSameDirection).ifPresentOrElse(
                elevator -> {
                    destinationService.addElevatorDestinationToGrabPerson(elevator, person.getCurrentFloorNumber());
                    person.setNeedService(false);
                },
                () -> {
                }
        );
    }

    private Optional<Elevator> chooseBestElevator(List<Elevator> noDestination, List<Elevator> personOnTheWayMovingSameDirection) {
        // Todo: add more logic to choose best elevator
        if (noDestination.isEmpty() && personOnTheWayMovingSameDirection.isEmpty())
            return Optional.empty();

        return Optional.of(noDestination.get(0));
    }
}
