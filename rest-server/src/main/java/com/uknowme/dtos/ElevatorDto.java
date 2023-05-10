package com.uknowme.dtos;

import java.util.List;

public class ElevatorDto {
    private int currentFloor;
    private List<PersonDto> people;

    public ElevatorDto() {
    }

    public ElevatorDto(int currentFloor, List<PersonDto> people) {
        this.currentFloor = currentFloor;
        this.people = people;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public List<PersonDto> getPeople() {
        return people;
    }

    public void setPeople(List<PersonDto> people) {
        this.people = people;
    }
}
