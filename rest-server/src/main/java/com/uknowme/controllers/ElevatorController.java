package com.uknowme.controllers;

import com.uknowme.domain.Elevator;
import com.uknowme.dtos.ElevatorDto;
import com.uknowme.mappers.DomainToDtoMapper;
import com.uknowme.services.IElevatorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class ElevatorController {
    private final IElevatorService elevatorService;

    @GetMapping("/buildings/{buildingId}/elevators")
    public List<ElevatorDto> getElevators(@PathVariable int buildingId) {
        List<Elevator> elevators = elevatorService.getBuildingElevators(buildingId);
        return DomainToDtoMapper.mapElevatorsToDto(elevators);
    }
}