package com.uknowme.domain.person;

import com.uknowme.domain.Building;
import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.Floor;
import jakarta.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SERIAL")
    private Integer id;
    private String name;
    private int startFloorNumber;
    private int desiredFloorNumber;
    private int currentFloorNumber;
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

    public Person(
            int id,
            String name,
            int startFloorNumber,
            int desiredFloorNumber,
            Building building,
            Direction direction,
            Elevator elevator, Floor floor) {
        this.id = id;
        this.name = name;
        this.startFloorNumber = startFloorNumber;
        this.desiredFloorNumber = desiredFloorNumber;
        this.currentFloorNumber = startFloorNumber;
        this.building = building;
        this.elevator = elevator;
        this.floor = floor;
        this.direction = direction;
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
}
