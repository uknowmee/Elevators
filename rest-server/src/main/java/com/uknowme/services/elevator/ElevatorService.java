package com.uknowme.services.elevator;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Person;

import java.util.List;
import java.util.stream.Stream;

public interface ElevatorService {

    List<Elevator> createElevators(int numOfElevators);

    void validateNumberOfElevators(int numOfElevators) throws ElevatorServiceException;

    void getPeopleOffElevators(List<Elevator> elevators);

    void addElevatorGoals(List<Elevator> elevators, List<Person> needsService);

    void changeStateOfElevators(List<Elevator> elevators);

    List<Elevator> getElevatorsReadyForEntering(List<Elevator> elevators);

    void moveElevators(List<Elevator> elevators);
}
