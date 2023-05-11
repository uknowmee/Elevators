package com.uknowme.repositories;

import com.uknowme.domain.elevator.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElevatorRepository extends JpaRepository<Elevator, Integer> {
}
