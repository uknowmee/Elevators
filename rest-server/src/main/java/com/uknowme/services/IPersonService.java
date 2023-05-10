package com.uknowme.services;

import com.uknowme.domain.person.Person;

import java.util.List;
import java.util.stream.Stream;

public interface IPersonService {
    public List<List<Person>> getFloorsPeople(Stream<Integer> floorIds);
    public List<List<Person>> getElevatorsPeople(Stream<Integer> elevatorIds);
    public List<Person> getAllPeopleFromElevator(int elevatorId);
    public List<Person> getAllPeopleFromFloor(int floorId);
    public Person createPerson(int startFloorNumber, int desiredFloorNumber, String name, int buildingId, int floorId);
}
