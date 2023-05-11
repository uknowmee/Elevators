package com.uknowme.services.elevator.impl;

import com.uknowme.domain.elevator.ElevatorDestination;
import com.uknowme.services.elevator.IElevatorDestinationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@AllArgsConstructor
@Service
public class ElevatorDestinationService implements IElevatorDestinationService {

    private ElevatorDestination createElevatorDestination() {
        ElevatorDestination elevatorDestination = new ElevatorDestination();
        elevatorDestination.setInitialTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        return elevatorDestination;
    }
}
