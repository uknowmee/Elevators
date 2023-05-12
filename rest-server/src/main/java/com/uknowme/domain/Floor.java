package com.uknowme.domain;

import com.uknowme.domain.person.Person;
import com.uknowme.dtos.FloorDto;
import com.uknowme.dtos.PersonDto;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int floorNumber;
    @ManyToOne
    private Building building;
    @OneToMany(mappedBy = "floor", fetch = FetchType.EAGER)
    private List<Person> people;

    public Floor() {
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

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", floorNumber=" + floorNumber +
                ", building=" + building +
                ", people=" + people.stream().map(Person::toString).collect(Collectors.joining("\n")) +
                '}';
    }
}
