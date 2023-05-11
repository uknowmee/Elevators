package com.uknowme.services.floor;

import com.uknowme.domain.Floor;

import java.util.List;

public interface FloorService {

    List<Floor> createFloors(int numOfFloors);

    void validateNumberOfFloors(int numOfFloors) throws FloorServiceException;
}
