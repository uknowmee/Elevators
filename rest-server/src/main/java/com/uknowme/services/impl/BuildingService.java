package com.uknowme.services.impl;

import com.uknowme.domain.Building;
import com.uknowme.domain.Floor;
import com.uknowme.domain.elevator.Elevator;
import com.uknowme.services.IBuildingService;
import com.uknowme.services.elevator.IElevatorService;
import com.uknowme.services.IFloorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BuildingService implements IBuildingService {
    private final IElevatorService elevatorService;
    private final IFloorService floorService;

    @Override
    public Building createBuildingWithFloorsAndElevators(int numOfFloors, int numOfElevators) {
        List<Floor> floors = floorService.createFloors(numOfFloors);
        List<Elevator> elevators = elevatorService.createElevators(numOfElevators);

        Building building = createBuilding();
        building.setNumOfFloors(numOfFloors);
        building.setNumOfElevators(numOfElevators);
        building.setFloors(floors);
        building.setElevators(elevators);

        return building;
    }

    private Building createBuilding(){
        Building building = new Building();
        building.setElevators(new ArrayList<>());
        building.setFloors(new ArrayList<>());
        building.setPeople(new ArrayList<>());
        return building;
    }
}
