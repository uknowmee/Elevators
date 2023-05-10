package com.uknowme.domain.person;

import com.uknowme.dtos.PersonDto;

public class Person {
    private int id;
    private String name;
    private int startFloorNumber;
    private int desiredFloorNumber;
    private int currentFloorNumber;
    private Direction direction;

    private Integer elevatorId;
    private Integer floorId;
    private int buildingId;

    public Person() {
    }

    public Person(
            int id,
            String name,
            int startFloorNumber,
            int desiredFloorNumber,
            int buildingId,
            int floorId,
            Direction direction
    ) {
        this.id = id;
        this.name = name;
        this.startFloorNumber = startFloorNumber;
        this.desiredFloorNumber = desiredFloorNumber;
        this.currentFloorNumber = startFloorNumber;
        this.buildingId = buildingId;
        this.floorId = floorId;
        this.direction = direction;
    }

    public PersonDto toDto() {
        return new PersonDto(this.name, this.direction, this.desiredFloorNumber);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartFloorNumber() {
        return startFloorNumber;
    }

    public void setStartFloorNumber(int startFloorNumber) {
        this.startFloorNumber = startFloorNumber;
    }

    public int getDesiredFloorNumber() {
        return desiredFloorNumber;
    }

    public void setDesiredFloorNumber(int desiredFloorNumber) {
        this.desiredFloorNumber = desiredFloorNumber;
    }

    public int getCurrentFloorNumber() {
        return currentFloorNumber;
    }

    public void setCurrentFloorNumber(int currentFloorNumber) {
        this.currentFloorNumber = currentFloorNumber;
    }

    public Integer getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(Integer elevatorId) {
        this.elevatorId = elevatorId;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
}
