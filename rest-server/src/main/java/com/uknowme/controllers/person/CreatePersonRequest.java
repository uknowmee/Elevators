package com.uknowme.controllers.person;

public class CreatePersonRequest {
    private int desiredFloorNumber;
    private String name;

    public CreatePersonRequest() {
    }

    public CreatePersonRequest(int desiredFloorNumber, String name) {
        this.desiredFloorNumber = desiredFloorNumber;
        this.name = name;
    }

    public int getDesiredFloorNumber() {
        return desiredFloorNumber;
    }

    public void setDesiredFloorNumber(int desiredFloorNumber) {
        this.desiredFloorNumber = desiredFloorNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
