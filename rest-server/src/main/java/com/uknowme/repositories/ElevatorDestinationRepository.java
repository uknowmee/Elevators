package com.uknowme.repositories;

import com.uknowme.domain.elevator.ElevatorDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElevatorDestinationRepository extends JpaRepository<ElevatorDestination, Integer> {
}
