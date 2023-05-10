package com.uknowme.dtos;

import java.util.List;

public class FloorDto {
    private int floorNumber;
    private List<PersonDto> people;

    public FloorDto() {
    }

    public FloorDto(int floorNumber, List<PersonDto> people) {
        this.floorNumber = floorNumber;
        this.people = people;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<PersonDto> getPeople() {
        return people;
    }

    public void setPeople(List<PersonDto> people) {
        this.people = people;
    }
}
