package com.uknowme.services.elevator.impl;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.services.elevator.IElevatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class ElevatorService implements IElevatorService {
    @Override
    public List<Elevator> getBuildingElevators(int buildingId) {
        return null;
    }

    @Override
    public List<Elevator> createElevators(int numOfElevators) {

        return IntStream
                .range(0, numOfElevators)
                .mapToObj(i -> createElevator())
                .collect(Collectors.toList());
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
