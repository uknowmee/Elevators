package com.uknowme.domain.person;

import com.uknowme.domain.Building;
import com.uknowme.domain.Floor;
import com.uknowme.domain.elevator.Elevator;
import jakarta.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int startFloorNumber;
    private int desiredFloorNumber;
    private int currentFloorNumber;
    private boolean needService;
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @ManyToOne(optional = true)
    @JoinColumn(name = "elevator_id", nullable = true)
    private Elevator elevator;
    @ManyToOne(optional = true)
    @JoinColumn(name = "floor_id", nullable = true)
    private Floor floor;
    @ManyToOne
    private Building building;

    public Person() {
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Building getBuildingId() {
        return building;
    }

    public void setBuildingId(Building building) {
        this.building = building;
    }

    public boolean isNeedService() {
        return needService;
    }

    public void setNeedService(boolean needsService) {
        this.needService = needsService;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", desiredFloorNumber=" + desiredFloorNumber +
                ", currentFloorNumber=" + currentFloorNumber +
                ", direction=" + direction +
                '}';
    }
}
