package com.uknowme.controllers.simulation;

public class CreateSimulationRequest {
    private int numOfFloors;
    private int numOfElevators;

    public CreateSimulationRequest() {
    }

    public CreateSimulationRequest(int numOfFloors, int numOfElevators) {
        this.numOfFloors = numOfFloors;
        this.numOfElevators = numOfElevators;
    }

    public int getNumOfFloors() {
        return numOfFloors;
    }

    public void setNumOfFloors(int numOfFloors) {
        this.numOfFloors = numOfFloors;
    }

    public int getNumOfElevators() {
        return numOfElevators;
    }

    public void setNumOfElevators(int numOfElevators) {
        this.numOfElevators = numOfElevators;
    }
}
