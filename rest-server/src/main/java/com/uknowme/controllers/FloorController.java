package com.uknowme.controllers;

import com.uknowme.domain.Floor;
import com.uknowme.dtos.FloorDto;
import com.uknowme.mappers.DomainToDtoMapper;
import com.uknowme.services.IFloorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/floor")
@AllArgsConstructor
public class FloorController {
    private final IFloorService floorService;

    @GetMapping("/get-floors/{buildingId}")
    public List<FloorDto> getFloors(@PathVariable int buildingId) {
        List<Floor> floors = floorService.getBuildingFloors(buildingId);
        return DomainToDtoMapper.mapFloorsToDto(floors);
    }

}