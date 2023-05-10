package com.uknowme.dtos;

import com.uknowme.domain.person.Direction;

public class PersonDto {
    private String name;
    private Direction direction;
    private int desiredFloorNumber;

    public PersonDto() {
    }

    public PersonDto(String name, Direction direction, int desiredFloorNumber) {
        this.name = name;
        this.direction = direction;
        this.desiredFloorNumber = desiredFloorNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getDesiredFloorNumber() {
        return desiredFloorNumber;
    }

    public void setDesiredFloorNumber(int desiredFloorNumber) {
        this.desiredFloorNumber = desiredFloorNumber;
    }
}
