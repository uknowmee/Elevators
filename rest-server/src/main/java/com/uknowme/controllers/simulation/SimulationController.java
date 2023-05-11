package com.uknowme.controllers.simulation;

import com.uknowme.domain.Simulation;
import com.uknowme.dtos.BuildingDetailsDto;
import com.uknowme.mappers.DomainToDtoMapper;
import com.uknowme.services.ISimulationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/simulations")
@AllArgsConstructor
public class SimulationController {

    private final ISimulationService simulationService;

    @PostMapping
    public BuildingDetailsDto createSimulation(
            @RequestBody CreateSimulationRequest request
    ) {
        Simulation simulation = simulationService.createSaveSimulation(
                request.getNumOfFloors(),
                request.getNumOfElevators()
        );
        return DomainToDtoMapper.mapBuildingToDto(simulation.getBuilding());
    }

    @GetMapping("/{buildingId}")
    public BuildingDetailsDto getSimulationStatus(@PathVariable int buildingId) {
        Simulation simulation = simulationService.getSimulation(buildingId);
        return DomainToDtoMapper.mapBuildingToDto(simulation.getBuilding());
    }

    @PutMapping("/{buildingId}/step")
    public BuildingDetailsDto makeSimulationStep(@PathVariable int buildingId) {
        Simulation simulation = simulationService.makeSimulationStep(buildingId);
        return DomainToDtoMapper.mapBuildingToDto(simulation.getBuilding());

    }

    @DeleteMapping("/{buildingId}")
    public void stopSimulation(@PathVariable int buildingId) {
        simulationService.stopSimulation(buildingId);
    }
}
