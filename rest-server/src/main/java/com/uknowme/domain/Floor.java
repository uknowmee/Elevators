package com.uknowme.domain;

import com.uknowme.dtos.FloorDto;
import com.uknowme.dtos.PersonDto;

import java.util.List;

public class Floor {
    private int id;
    private int floorNumber;

    private int buildingId;

    public Floor() {
    }

    public Floor(int id, int floorNumber, int buildingId) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.buildingId = buildingId;
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

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
}
