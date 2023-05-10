package com.uknowme.mappers;

import com.uknowme.domain.Building;
import com.uknowme.domain.Elevator;
import com.uknowme.domain.Floor;
import com.uknowme.domain.Simulation;
import com.uknowme.domain.person.Person;
import com.uknowme.dtos.*;
import org.springframework.data.util.StreamUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DomainToDtoMapper {
    public static List<ElevatorDto> mapElevatorsToDtoWithPeople(
            List<Elevator> elevators,
            List<List<Person>> elevatorsPeople
    ) {
        return StreamUtils
                .zip(
                        elevators.stream(),
                        elevatorsPeople.stream(),
                        (elevator, people) -> elevator.toDto(people.stream().map(Person::toDto).toList())
                )
                .collect(Collectors.toList());
    }

    public static List<FloorDto> mapFloorsToDtoWithPeople(
            List<Floor> floors,
            List<List<Person>> people
    ) {
        return StreamUtils
                .zip(
                        floors.stream(),
                        people.stream(),
                        (floor, floorPeople) -> floor.toDto(floorPeople.stream().map(Person::toDto).toList())
                )
                .collect(Collectors.toList());
    }

    public static PersonDto mapPersonToDto(Person person) {
        return person.toDto();
    }

    public static SimulationDto mapSimulationToDto(Simulation simulation) {
        return simulation.toDto();
    }

    public static BuildingDto mapBuildingToDto(Building building) {
        return building.toDto();
    }
}
