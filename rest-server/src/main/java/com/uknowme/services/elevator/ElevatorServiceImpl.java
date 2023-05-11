package com.uknowme.services.elevator;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.repositories.BuildingRepository;
import com.uknowme.repositories.ElevatorRepository;
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

    private final ElevatorRepository elevatorRepository;

    @Override
    public List<Elevator> createElevators(int numOfElevators) {

        return IntStream
                .range(0, numOfElevators)
                .mapToObj(i -> createElevator())
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

    private Elevator createElevator() {
        Elevator elevator = new Elevator();
        elevator.setElevatorDestinations(new ArrayList<>());
        elevator.setPeople(new ArrayList<>());

        elevator.setCurrentFloor(0);
        elevator.setDestinationFloor(0);
        elevator.setOpened(false);

        return elevator;
    }
}
