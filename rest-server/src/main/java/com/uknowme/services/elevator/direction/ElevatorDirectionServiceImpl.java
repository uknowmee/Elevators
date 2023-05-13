package com.uknowme.services.elevator.direction;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.person.Direction;
import org.springframework.stereotype.Service;


@Service
public class ElevatorDirectionServiceImpl implements ElevatorDirectionService {
    @Override
    public Direction getElevatorDirection(Elevator elevator) {
        return elevator.getCurrentFloor() < elevator.getDestinationFloor() ? Direction.UP : Direction.DOWN;
    }
}
