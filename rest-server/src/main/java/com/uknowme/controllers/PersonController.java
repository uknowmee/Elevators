package com.uknowme.controllers;

import com.uknowme.domain.person.Person;
import com.uknowme.dtos.PersonDto;
import com.uknowme.mappers.DomainToDtoMapper;
import com.uknowme.services.IFloorService;
import com.uknowme.services.IPersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/person")
@AllArgsConstructor
public class PersonController {
    private final IPersonService personService;
    private final IFloorService floorService;

    @PostMapping("/create-person/{buildingId}/{elevatorId}")
    public PersonDto createPerson(
            @RequestBody int startFloorNumber,
            @RequestBody int desiredFloorNumber,
            @RequestBody String name,
            @PathVariable int buildingId,
            @PathVariable int elevatorId
    ) {

        Person person = personService.createPerson(
                startFloorNumber,
                desiredFloorNumber,
                name,
                buildingId,
                elevatorId
        );

        return DomainToDtoMapper.mapPersonToDto(person);
    }
}
