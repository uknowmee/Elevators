package com.uknowme.controllers.person;

import com.uknowme.controllers.CoreException;
import com.uknowme.domain.person.Person;
import com.uknowme.dtos.PersonDto;
import com.uknowme.mappers.DomainToDtoMapper;
import com.uknowme.services.person.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping("/buildings/{buildingId}/floors/{floorNumber}/people")
    public ResponseEntity<PersonDto> createPerson(
            @RequestBody CreatePersonRequest request,
            @PathVariable int floorNumber,
            @PathVariable int buildingId
    ) {

        Person person = personService.createValidPerson(
                floorNumber,
                request.getDesiredFloorNumber(),
                request.getName(),
                buildingId
        );

        PersonDto personDto = DomainToDtoMapper.mapPersonToDto(person);

        return new ResponseEntity<>(personDto, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(CoreException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
