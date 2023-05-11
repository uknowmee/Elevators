package com.uknowme.services.impl;

import com.uknowme.domain.Floor;
import com.uknowme.services.IFloorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class FloorService implements IFloorService {
    @Override
    public List<Floor> getBuildingFloors(int buildingId) {
        return null;
    }

    @Override
    public List<Floor> createFloors(int numOfFloors) {

        return IntStream
                .range(0, numOfFloors)
                .mapToObj(this::createFloor)
                .collect(Collectors.toList());
    }

    private Floor createFloor(int floorNumber) {
        Floor floor = new Floor();
        floor.setPeople(new ArrayList<>());
        floor.setFloorNumber(floorNumber);
        return floor;
    }
}
