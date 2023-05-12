package com.uknowme.services.building;

import com.uknowme.domain.Building;
import com.uknowme.domain.Floor;
import com.uknowme.domain.elevator.Elevator;
import com.uknowme.repositories.BuildingRepository;
import com.uknowme.services.elevator.ElevatorService;
import com.uknowme.services.floor.FloorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BuildingServiceImpl implements BuildingService {
    private final ElevatorService elevatorService;
    private final FloorService floorService;
    private final BuildingRepository buildingRepository;

    @Override
    public Building createBuildingWithFloorsAndElevators(int numOfFloors, int numOfElevators) {
        List<Floor> floors = floorService.createFloors(numOfFloors + 1);
        List<Elevator> elevators = elevatorService.createElevators(numOfElevators);

        Building building = createBuilding();
        building.setNumOfFloors(numOfFloors + 1);
        building.setNumOfElevators(numOfElevators);
        building.setFloors(floors);
        building.setElevators(elevators);

        return building;
    }

    @Override
    public Building getBuilding(int buildingId) throws BuildingServiceException {
        return buildingRepository.findById(buildingId)
                .orElseThrow(() -> new BuildingServiceException(
                        BuildingServiceErrorCode.BUILDING_NOT_FOUND,
                        HttpStatus.NOT_FOUND,
                        BuildingServiceException.BUILDING_NOT_FOUND_MESSAGE
                ));
    }

    private Building createBuilding() {
        Building building = new Building();
        building.setElevators(new ArrayList<>());
        building.setFloors(new ArrayList<>());
        building.setPeople(new ArrayList<>());
        return building;
    }
}
