package com.uknowme.services;

import com.uknowme.domain.Building;

public interface IBuildingService {

    Building createBuildingWithFloorsAndElevators(int numOfFloors, int numOfElevators);
}
