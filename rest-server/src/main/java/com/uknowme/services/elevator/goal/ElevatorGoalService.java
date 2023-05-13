package com.uknowme.services.elevator.goal;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Person;

import java.util.List;

public interface ElevatorGoalService {
    void tryToServe(List<Elevator> elevators, Person person);
}
