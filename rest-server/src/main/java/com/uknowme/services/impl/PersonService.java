package com.uknowme.services.impl;

import com.uknowme.domain.person.Person;
import com.uknowme.services.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class PersonService implements IPersonService {
    @Override
    public List<List<Person>> getFloorsPeople(Stream<Integer> floorIds) {
        return null;
    }

    @Override
    public List<List<Person>> getElevatorsPeople(Stream<Integer> elevatorIds) {
        return null;
    }

    @Override
    public List<Person> getAllPeopleFromElevator(int elevatorId) {
        return null;
    }

    @Override
    public List<Person> getAllPeopleFromFloor(int floorId) {
        return null;
    }

    @Override
    public Person createPerson(int startFloorNumber, int desiredFloorNumber, String name, int buildingId, int floorId) {
        return null;
    }
}
