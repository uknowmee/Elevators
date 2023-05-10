package com.uknowme.controllers;

import com.uknowme.domain.Floor;
import com.uknowme.domain.person.Person;
import com.uknowme.dtos.FloorDto;
import com.uknowme.mappers.DomainToDtoMapper;
import com.uknowme.services.IFloorService;
import com.uknowme.services.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/floor")
@AllArgsConstructor
public class FloorController {
    private final IFloorService floorService;
    private final IPersonService personService;

    @GetMapping("/get-floors/{buildingId}")
    public List<FloorDto> getFloors(@PathVariable int buildingId) {
        List<Floor> floors = floorService.getBuildingFloors(buildingId);
        List<List<Person>> people = personService.getFloorsPeople(floors.stream().map(Floor::getFloorNumber));

        return DomainToDtoMapper.mapFloorsToDtoWithPeople(floors, people);
    }

}