package com.uknowme.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SERIAL")
    private Long id;
    private Date startTime;
    private Date endTime;
    @OneToOne(mappedBy="simulation")
    private Building building;

    public Simulation() {
    }

    public Simulation(Long id, Date startTime, Date endTime, Building building) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.building = building;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
