package com.uknowme.services.impl;

import com.uknowme.domain.Building;
import com.uknowme.services.IBuildingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuildingService implements IBuildingService {
    @Override
    public Building getBuildingDetails(int buildingId) {
        return null;
    }
}
