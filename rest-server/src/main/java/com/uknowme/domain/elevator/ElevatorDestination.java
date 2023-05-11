package com.uknowme.domain.elevator;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ElevatorDestination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "SERIAL")
    private Integer id;
    private int floorNumber;
    private Date initialTime;
    @ManyToOne
    private Elevator elevator;


    public ElevatorDestination() {
    }

    public ElevatorDestination(Integer id, int floorNumber, Date initialTime, Elevator elevator) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.initialTime = initialTime;
        this.elevator = elevator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public Date getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(Date initialTime) {
        this.initialTime = initialTime;
    }
}
