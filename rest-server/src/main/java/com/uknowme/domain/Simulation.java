package com.uknowme.domain;

import com.uknowme.dtos.SimulationDto;

import java.util.Date;

public class Simulation {
    private int id;
    private int buildingId;
    private Date startTime;

    public Simulation() {
    }

    public Simulation(int id, int buildingId, Date startTime) {
        this.id = id;
        this.buildingId = buildingId;
        this.startTime = startTime;
    }

    public SimulationDto toDto(){
        return new SimulationDto();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
