package com.uknowme.domain;

import com.uknowme.domain.person.Person;
import com.uknowme.dtos.FloorDto;
import com.uknowme.dtos.PersonDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Floor {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private int floorNumber;
    @ManyToOne
    private Building building;
    @OneToMany(mappedBy = "floor")
    private List<Person> people;

    public Floor() {
    }

    public Floor(int id, int floorNumber, Building building, List<Person> people) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.building = building;
        this.people = people;
    }

    public FloorDto toDto(List<PersonDto> people) {
        return new FloorDto(this.floorNumber, people);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
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
