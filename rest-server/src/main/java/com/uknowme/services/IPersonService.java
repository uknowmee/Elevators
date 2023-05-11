package com.uknowme.services;

import com.uknowme.domain.person.Person;

public interface IPersonService {
    public Person createPerson(int startFloorNumber, int desiredFloorNumber, String name, int buildingId);
}
