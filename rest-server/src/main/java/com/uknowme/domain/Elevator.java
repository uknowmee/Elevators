package com.uknowme.domain;

import com.uknowme.domain.person.Person;
import com.uknowme.dtos.ElevatorDto;
import com.uknowme.dtos.PersonDto;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Elevator {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private int currentFloor;
    private int destinationFloor;
    @ManyToOne
    private Building building;
    @OneToMany(mappedBy = "elevator")
    private List<Person> people;

    public Elevator() {
    }

    public Elevator(int id, int currentFloor, int destinationFloor, Building building, List<Person> people) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.building = building;
        this.people = people;
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
