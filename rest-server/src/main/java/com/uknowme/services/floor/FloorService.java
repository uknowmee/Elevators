package com.uknowme.services.floor;

import com.uknowme.domain.Floor;

import java.util.List;

public interface FloorService {

    void validateNumberOfFloors(int numOfFloors) throws FloorServiceException;

    List<Floor> createFloors(int numOfFloors);
}
