package com.uknowme.controllers;

import com.uknowme.domain.Building;
import com.uknowme.domain.Simulation;
import com.uknowme.dtos.BuildingDto;
import com.uknowme.dtos.SimulationDto;
import com.uknowme.mappers.DomainToDtoMapper;
import com.uknowme.services.IBuildingService;
import com.uknowme.services.ISimulationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/simulation")
@AllArgsConstructor
public class SimulationController {

    private final ISimulationService simulationService;
    private final IBuildingService buildingService;

    @PostMapping
    public BuildingDto createSimulation(
            @RequestBody int numOfFloors,
            @RequestBody int numOfElevators
    ) {
        Simulation simulation = simulationService.createSimulation(numOfFloors, numOfElevators);
        Building building = buildingService.getSimulationBuilding(simulation.getBuildingId());

        return DomainToDtoMapper.mapBuildingToDto(building);
    }

    @GetMapping("/{buildingId}")
    public SimulationDto getSimulationStatus(@PathVariable int buildingId) {
//        return simulationService.getSimulation();
        return null;
    }

    @PutMapping("/make-step/{buildingId}")
    public SimulationDto makeSimulationStep(@PathVariable int buildingId) {
//        simulationService.makeSimulationStep();
//        return simulationService.getSimulation();
        return null;

    }

    @DeleteMapping("/{buildingId}")
    public void stopSimulation(@PathVariable int buildingId) {
//        simulationService.stopSimulation();
    }
}
