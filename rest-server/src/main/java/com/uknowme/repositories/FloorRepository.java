package com.uknowme.repositories;

import com.uknowme.domain.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer> {
    List<Floor> findAllByBuildingId(int buildingId);
}
