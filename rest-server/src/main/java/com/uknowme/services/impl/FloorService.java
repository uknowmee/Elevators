package com.uknowme.services.impl;

import com.uknowme.domain.Floor;
import com.uknowme.services.IFloorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FloorService implements IFloorService {
    @Override
    public List<Floor> getBuildingFloors(int buildingId) {
        return null;
    }
}
