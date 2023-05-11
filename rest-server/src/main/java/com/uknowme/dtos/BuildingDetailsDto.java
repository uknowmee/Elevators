package com.uknowme.dtos;

import java.util.List;

public class BuildingDetailsDto {
    private List<ElevatorDto> elevators;
    private List<FloorDto> floors;

    public BuildingDetailsDto() {
    }

    public BuildingDetailsDto(List<ElevatorDto> elevators, List<FloorDto> floors) {
        this.elevators = elevators;
        this.floors = floors;
    }

    public List<ElevatorDto> getElevators() {
        return elevators;
    }

    public void setElevators(List<ElevatorDto> elevators) {
        this.elevators = elevators;
    }

    public List<FloorDto> getFloors() {
        return floors;
    }

    public void setFloors(List<FloorDto> floors) {
        this.floors = floors;
    }
}
