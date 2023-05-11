package com.uknowme.services.impl;

import com.uknowme.domain.person.Person;
import com.uknowme.services.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonService implements IPersonService {
    @Override
    public Person createPerson(int startFloorNumber, int desiredFloorNumber, String name, int buildingId) {
        return null;
    }
}
