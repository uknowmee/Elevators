package com.uknowme.services;

import com.uknowme.domain.Floor;

import java.util.List;

public interface IFloorService {
    public List<Floor> getBuildingFloors(int buildingId);

    List<Floor> createFloors(int numOfFloors);
}
