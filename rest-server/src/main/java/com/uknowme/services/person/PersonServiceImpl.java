package com.uknowme.services.person;

import com.uknowme.domain.Building;
import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Direction;
import com.uknowme.domain.person.Person;
import com.uknowme.repositories.PersonRepository;
import com.uknowme.services.building.BuildingService;
import com.uknowme.services.elevatorDestination.ElevatorDestinationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final BuildingService buildingService;
    private final ElevatorDestinationService elevatorDestinationService;
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

    @Override
    public List<Person> getPeopleWhoNeedService(List<Person> peopleInBuilding) {
        return peopleInBuilding.stream().filter(Person::isNeedService).toList();
    }

    @Override
    public void enterElevator(List<Person> needsService, List<Elevator> elevators) {
        List<Elevator> openedElevators = elevators.stream().filter(Elevator::isOpened).toList();

        if (openedElevators.isEmpty()) return;

        needsService.forEach(person -> {
                    List<Elevator> fineForPerson = openedElevators.stream()
                            .filter(elevator -> elevator.getCurrentFloor() == person.getCurrentFloorNumber())
                            .toList();

                    if (fineForPerson.isEmpty()) return;

                    Elevator elevator = fineForPerson.get((int) (Math.random() * fineForPerson.size()));

                    movePersonToElevator(person, elevator);
                    elevatorDestinationService.addElevatorDestinationToDeliverPerson(elevator, person.getDesiredFloorNumber());

                    person.setNeedService(false);
                }
        );
    }

    @Override
    public List<Person> getPeopleNotInElevatorsButOnFloors(List<Person> peopleInBuilding) {
        return peopleInBuilding.stream().filter(person -> person.getElevator() == null && person.getFloor() != null).toList();
    }

    private void movePersonToElevator(Person person, Elevator elevator) {
        person.getFloor().getPeople().remove(person);
        person.setFloor(null);
        person.setElevator(elevator);
        elevator.getPeople().add(person);
    }

    private void checkDifferent(int startFloorNumber, int desiredFloorNumber) {
        if (startFloorNumber == desiredFloorNumber)
            throw new PersonServiceException(
                    PersonServiceErrorCode.START_AND_DESIRED_FLOOR_NUMBER_ARE_SAME,
                    HttpStatus.BAD_REQUEST,
                    PersonServiceException.START_AND_DESIRED_FLOOR_NUMBER_ARE_SAME_MESSAGE
            );
    }

    private void validateStartFloorNumber(int startFloorNumber, Building building) throws PersonServiceException {
        if (startFloorNumber < 0 || startFloorNumber > building.getNumOfFloors())
            throw new PersonServiceException(
                    PersonServiceErrorCode.INVALID_START_FLOOR_NUMBER,
                    HttpStatus.BAD_REQUEST,
                    PersonServiceException.INVALID_START_FLOOR_NUMBER_MESSAGE
            );
    }

    private void validateDesiredFloorNumber(int desiredFloorNumber, Building building) throws PersonServiceException {
        if (desiredFloorNumber < 0 || desiredFloorNumber > building.getNumOfFloors())
            throw new PersonServiceException(
                    PersonServiceErrorCode.INVALID_DESIRED_FLOOR_NUMBER,
                    HttpStatus.BAD_REQUEST,
                    PersonServiceException.INVALID_DESIRED_FLOOR_NUMBER_MESSAGE
            );
    }

    private Person createPerson(int startFloorNumber, int desiredFloorNumber, String name) {
        Person person = new Person();
        person.setNeedService(true);
        person.setElevator(null);
        person.setStartFloorNumber(startFloorNumber);
        person.setDesiredFloorNumber(desiredFloorNumber);
        person.setCurrentFloorNumber(startFloorNumber);
        person.setName(name);
        person.setDirection(startFloorNumber < desiredFloorNumber ? Direction.UP : Direction.DOWN);
        return person;
    }
}
