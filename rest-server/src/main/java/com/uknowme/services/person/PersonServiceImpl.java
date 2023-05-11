package com.uknowme.services.person;

import com.uknowme.domain.Building;
import com.uknowme.domain.person.Direction;
import com.uknowme.domain.person.Person;
import com.uknowme.repositories.PersonRepository;
import com.uknowme.services.building.BuildingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final BuildingService buildingService;
    private final PersonRepository personRepository;

    @Override
    public Person createValidPerson(int startFloorNumber, int desiredFloorNumber, String name, int buildingId) {
        Building building = buildingService.getBuilding(buildingId);

        validateStartFloorNumber(startFloorNumber, building);
        validateDesiredFloorNumber(desiredFloorNumber, building);
        checkDifferent(startFloorNumber, desiredFloorNumber);

        Person person = createPerson(startFloorNumber, desiredFloorNumber, name);
        person.setBuilding(building);
        person.setFloor(building.getFloors().get(startFloorNumber));

        return personRepository.save(person);
    }

    private void checkDifferent(int startFloorNumber, int desiredFloorNumber) {
        if (startFloorNumber == desiredFloorNumber)
            throw new PersonServiceException(
                    PersonServiceErrorCode.START_AND_DESIRED_FLOOR_NUMBER_ARE_SAME,
                    HttpStatus.BAD_REQUEST,
                    PersonServiceException.START_AND_DESIRED_FLOOR_NUMBER_ARE_SAME_MESSAGE
            );
    }

    private void validateStartFloorNumber(int startFloorNumber, Building building) throws PersonServiceException{
        if (startFloorNumber < 0 || startFloorNumber > building.getFloors().size())
            throw new PersonServiceException(
                    PersonServiceErrorCode.INVALID_START_FLOOR_NUMBER,
                    HttpStatus.BAD_REQUEST,
                    PersonServiceException.INVALID_START_FLOOR_NUMBER_MESSAGE
            );
    }

    private void validateDesiredFloorNumber(int desiredFloorNumber, Building building) throws PersonServiceException {
        if (desiredFloorNumber < 0 || desiredFloorNumber > building.getFloors().size())
            throw new PersonServiceException(
                    PersonServiceErrorCode.INVALID_DESIRED_FLOOR_NUMBER,
                    HttpStatus.BAD_REQUEST,
                    PersonServiceException.INVALID_DESIRED_FLOOR_NUMBER_MESSAGE
            );
    }

    private Person createPerson(int startFloorNumber, int desiredFloorNumber, String name) {
        Person person = new Person();
        person.setElevator(null);
        person.setStartFloorNumber(startFloorNumber);
        person.setDesiredFloorNumber(desiredFloorNumber);
        person.setCurrentFloorNumber(startFloorNumber);
        person.setName(name);
        person.setDirection(startFloorNumber < desiredFloorNumber ? Direction.UP : Direction.DOWN);
        return person;
    }
}
