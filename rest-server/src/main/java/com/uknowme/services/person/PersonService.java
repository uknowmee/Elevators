package com.uknowme.services.person;

import com.uknowme.domain.person.Person;

public interface PersonService {
    public Person createValidPerson(int startFloorNumber, int desiredFloorNumber, String name, int buildingId);
}
