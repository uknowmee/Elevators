package com.uknowme.controllers.person;

import com.uknowme.domain.person.Person;
import com.uknowme.dtos.PersonDto;
import com.uknowme.mappers.DomainToDtoMapper;
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
            @RequestBody CreatePersonRequest request,
            @PathVariable int floorNumber,
            @PathVariable int buildingId
    ) {

        Person person = personService.createPerson(
                floorNumber,
                request.getDesiredFloorNumber(),
                request.getName(),
                buildingId
        );

        return DomainToDtoMapper.mapPersonToDto(person);
    }
}
