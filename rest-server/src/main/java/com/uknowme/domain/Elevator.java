package com.uknowme.domain;

import com.uknowme.dtos.ElevatorDto;
import com.uknowme.dtos.PersonDto;

import java.util.List;

public class Elevator {
    private int id;
    private int currentFloor;
    private int destinationFloor;

    private int buildingId;

    public Elevator() {
    }

    public Elevator(int id, int currentFloor, int destinationFloor, int buildingId) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.buildingId = buildingId;
    }

    public ElevatorDto toDto(List<PersonDto> people) {
        return new ElevatorDto(this.currentFloor, people);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
}
