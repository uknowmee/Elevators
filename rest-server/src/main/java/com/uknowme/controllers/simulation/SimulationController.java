package com.uknowme.controllers.simulation;

import com.uknowme.controllers.CoreException;
import com.uknowme.domain.Simulation;
import com.uknowme.dtos.BuildingDetailsDto;
import com.uknowme.mappers.DomainToDtoMapper;
import com.uknowme.services.simulation.SimulationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/simulations")
@AllArgsConstructor
public class SimulationController {

    private final SimulationService simulationService;

    @PostMapping
    public ResponseEntity<BuildingDetailsDto> createSimulation(@RequestBody CreateSimulationRequest request) {

            Simulation simulation = simulationService.createSaveSimulation(
                    request.getNumOfFloors(),
                    request.getNumOfElevators()
            );

            BuildingDetailsDto buildingDetailsDto = DomainToDtoMapper.mapBuildingToDto(simulation.getBuilding());

            return new ResponseEntity<>(buildingDetailsDto, HttpStatus.CREATED);
    }

    @PutMapping("/{buildingId}/step")
    public ResponseEntity<BuildingDetailsDto> makeSimulationStep(@PathVariable int buildingId) {

        Simulation simulation = simulationService.makeSimulationStep(buildingId);

        BuildingDetailsDto buildingDetailsDto = DomainToDtoMapper.mapBuildingToDto(simulation.getBuilding());

        return new ResponseEntity<>(buildingDetailsDto, HttpStatus.OK);
    }

    @PutMapping("/{buildingId}/stop")
    public ResponseEntity<String> stopSimulation(@PathVariable int buildingId) {

        simulationService.stopSimulation(buildingId);

        return new ResponseEntity<>("Simulation stopped", HttpStatus.OK);
    }

    @GetMapping("/{buildingId}")
    public ResponseEntity<BuildingDetailsDto> getSimulationStatus(@PathVariable int buildingId) {
        Simulation simulation = simulationService.getSimulation(buildingId);

        BuildingDetailsDto buildingDetailsDto = DomainToDtoMapper.mapBuildingToDto(simulation.getBuilding());

        return new ResponseEntity<>(buildingDetailsDto, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(CoreException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
