package com.uknowme.services.building;

import com.uknowme.domain.Building;

public interface BuildingService {

    Building createBuildingWithFloorsAndElevators(int numOfFloors, int numOfElevators);

    Building getBuilding(int buildingId) throws BuildingServiceException;
}
