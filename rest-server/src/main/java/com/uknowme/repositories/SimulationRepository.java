package com.uknowme.repositories;

import com.uknowme.domain.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SimulationRepository extends JpaRepository<Simulation, Integer> {
    Optional<Simulation> findSimulationByBuildingId(Integer buildingId);
}
