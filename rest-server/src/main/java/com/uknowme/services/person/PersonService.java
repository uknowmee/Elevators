package com.uknowme.services.person;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Person;

import java.util.List;

public interface PersonService {
    void enterElevator(List<Person> needsService, List<Elevator> elevators);

    Person createValidPerson(int startFloorNumber, int desiredFloorNumber, String name, int buildingId);

    List<Person> getPeopleWhoNeedService(List<Person> peopleInBuilding);

    List<Person> getPeopleNotInElevatorsButOnFloors(List<Person> peopleInBuilding);
}
