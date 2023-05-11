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
@RequestMapping("api/v1")
@AllArgsConstructor
public class PersonController {
    private final IPersonService personService;

    @PostMapping("/buildings/{buildingId}/floors/{floorNumber}/people")
    public PersonDto createPerson(
            @RequestBody int desiredFloorNumber,
            @RequestBody String name,
            @PathVariable int floorNumber,
            @PathVariable int buildingId
    ) {

        Person person = personService.createPerson(
                floorNumber,
                desiredFloorNumber,
                name,
                buildingId
        );

        return DomainToDtoMapper.mapPersonToDto(person);
    }
}
