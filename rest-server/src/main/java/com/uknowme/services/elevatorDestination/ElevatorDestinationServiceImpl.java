package com.uknowme.services.elevatorDestination;

import com.uknowme.domain.elevator.ElevatorDestination;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@AllArgsConstructor
@Service
public class ElevatorDestinationServiceImpl implements ElevatorDestinationService {

    private ElevatorDestination createElevatorDestination() {
        ElevatorDestination elevatorDestination = new ElevatorDestination();
        elevatorDestination.setInitialTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        return elevatorDestination;
    }
}
