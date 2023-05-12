package com.uknowme.mappers;

import com.uknowme.domain.Building;
import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.Floor;
import com.uknowme.domain.person.Person;
import com.uknowme.dtos.BuildingDetailsDto;
import com.uknowme.dtos.ElevatorDto;
import com.uknowme.dtos.FloorDto;
import com.uknowme.dtos.PersonDto;

import java.util.List;

public class DomainToDtoMapper {
    public static List<ElevatorDto> mapElevatorsToDto(List<Elevator> elevators) {
        return elevators.stream().map(DomainToDtoMapper::mapElevatorToDto).toList();
    }

    public static List<FloorDto> mapFloorsToDto(List<Floor> floors) {
        return floors.stream().map(DomainToDtoMapper::mapFloorToDto).toList();
    }

    public static List<PersonDto> mapPeopleToDto(List<Person> people) {
        return people.stream().map(DomainToDtoMapper::mapPersonToDto).toList();
    }

    public static ElevatorDto mapElevatorToDto(Elevator elevator) {
        return new ElevatorDto(
                elevator.getCurrentFloor(),
                elevator.getState(),
                elevator.getDestinationFloor(),
                elevator.getSerialNumber(),
                elevator.isOpened(),
                mapPeopleToDto(elevator.getPeople())
        );
    }

    public static FloorDto mapFloorToDto(Floor floor) {
        return new FloorDto(
                floor.getFloorNumber(),
                mapPeopleToDto(floor.getPeople())
        );
    }

    public static PersonDto mapPersonToDto(Person person) {
        return new PersonDto(
                person.getName(),
                person.getDirection(),
                person.getDesiredFloorNumber()
        );
    }

    public static BuildingDetailsDto mapBuildingToDto(Building building) {
        return new BuildingDetailsDto(
                mapElevatorsToDto(building.getElevators()),
                mapFloorsToDto(building.getFloors()),
                building.getId());
    }
}
