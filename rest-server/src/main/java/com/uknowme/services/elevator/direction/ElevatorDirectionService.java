package com.uknowme.services.elevator.direction;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Direction;

public interface ElevatorDirectionService {
    Direction getElevatorDirection(Elevator elevator);
}
