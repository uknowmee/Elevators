package com.uknowme.services.elevator.move;

import com.uknowme.domain.elevator.Elevator;
import com.uknowme.domain.elevator.ElevatorState;
import com.uknowme.domain.person.Direction;
import com.uknowme.services.elevator.direction.ElevatorDirectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ElevatorMoveServiceImpl implements ElevatorMoveService{

    private final ElevatorDirectionService directionService;

    @Override
    public void moveElevator(Elevator elevator) {
        if (elevator.getState() != ElevatorState.MOVING) return;
        Direction direction = directionService.getElevatorDirection(elevator);
        elevator.setCurrentFloor(direction == Direction.UP ? elevator.getCurrentFloor() + 1 : elevator.getCurrentFloor() - 1);
    }
}
