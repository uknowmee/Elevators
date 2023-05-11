package com.uknowme.repositories;

import com.uknowme.domain.elevator.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElevatorRepository extends JpaRepository<Elevator, Integer> {
    List<Elevator> findAllByBuildingId(int buildingId);
}
