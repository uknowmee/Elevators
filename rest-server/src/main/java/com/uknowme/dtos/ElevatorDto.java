package com.uknowme.dtos;

import com.uknowme.domain.elevator.ElevatorState;

import java.util.List;

public class ElevatorDto {
    private int currentFloor;
    private ElevatorState state;
    private int destinationFloor;
    private int serialNumber;
    private boolean isOpened;
    private List<PersonDto> people;

    public ElevatorDto() {
    }

    public ElevatorDto(int currentFloor, ElevatorState state, int destinationFloor, int serialNumber, boolean isOpened, List<PersonDto> people) {
        this.currentFloor = currentFloor;
        this.state = state;
        this.destinationFloor = destinationFloor;
        this.serialNumber = serialNumber;
        this.isOpened = isOpened;
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

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }
}
