package com.uknowme.domain;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Person;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int numOfFloors;
    private int numOfElevators;
    @OneToOne
    private Simulation simulation;
    @OneToMany(mappedBy = "building", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Elevator> elevators;
    @OneToMany(mappedBy = "building", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Floor> floors;
    @OneToMany(mappedBy = "building", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Person> people;

    public Building() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public void setElevators(List<Elevator> elevators) {
        this.elevators = elevators;
        elevators.forEach(elevator -> elevator.setBuilding(this));
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
        floors.forEach(floor -> floor.setBuilding(this));
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
        people.forEach(person -> person.setBuilding(this));
    }

    public void addPerson(Person person) {
        this.people.add(person);
        person.setBuilding(this);
    }
}
