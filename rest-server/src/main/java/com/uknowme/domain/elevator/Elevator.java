package com.uknowme.domain.elevator;

import com.uknowme.domain.Building;
import com.uknowme.domain.person.Person;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Elevator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int currentFloor;
    private int destinationFloor;
    private boolean isOpened;
    @ManyToOne
    private Building building;
    @OneToMany(mappedBy = "elevator")
    private List<Person> people;
    @OneToMany(mappedBy = "elevator")
    private List<ElevatorDestination> elevatorDestinations;

    public Elevator() {
    }

    public Elevator(int id, int currentFloor, int destinationFloor, boolean isOpened, Building building, List<Person> people) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.isOpened = isOpened;
        this.building = building;
        this.people = people;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
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

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}