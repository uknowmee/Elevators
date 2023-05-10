package com.uknowme.controllers;

import com.uknowme.domain.Elevator;
import com.uknowme.domain.person.Person;
import com.uknowme.dtos.ElevatorDto;
import com.uknowme.mappers.DomainToDtoMapper;
import com.uknowme.services.IElevatorService;
import com.uknowme.services.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/elevator")
@AllArgsConstructor
public class ElevatorController {

    private final IElevatorService elevatorService;
    private final IPersonService personService;

    @GetMapping("/get-elevators/{buildingId}")
    public List<ElevatorDto> getElevators(@PathVariable int buildingId) {
        List<Elevator> elevators = elevatorService.getBuildingElevators(buildingId);
        List<List<Person>> elevatorsPeople = personService.getElevatorsPeople(elevators.stream().map(Elevator::getId));

        return DomainToDtoMapper.mapElevatorsToDtoWithPeople(elevators, elevatorsPeople);
    }
}