package com.uknowme.services.floor;

import com.uknowme.domain.Floor;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class FloorServiceImpl implements FloorService {

    private static final int MIN_FLOOR_NUMBER = 8;
    private static final int MAX_FLOOR_NUMBER = 17;

    @Override
    public List<Floor> createFloors(int numOfFloors) {

        return IntStream
                .range(0, numOfFloors)
                .mapToObj(this::createFloor)
                .collect(Collectors.toList());
    }

    @Override
    public void validateNumberOfFloors(int numOfFloors) throws FloorServiceException {
        if (numOfFloors < MIN_FLOOR_NUMBER || numOfFloors > MAX_FLOOR_NUMBER)
            throw new FloorServiceException(
                    FloorServiceErrorCode.INVALID_NUMBER_OF_FLOORS,
                    HttpStatus.BAD_REQUEST,
                    FloorServiceException.INVALID_NUMBER_OF_FLOORS_MESSAGE
            );
    }

    private Floor createFloor(int floorNumber) {
        Floor floor = new Floor();
        floor.setPeople(new ArrayList<>());
        floor.setFloorNumber(floorNumber);
        return floor;
    }
}
