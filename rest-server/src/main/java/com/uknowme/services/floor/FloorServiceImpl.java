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

    @Override
    public List<Floor> createFloors(int numOfFloors) {

        return IntStream
                .range(0, numOfFloors)
                .mapToObj(this::createFloor)
                .collect(Collectors.toList());
    }

    @Override
    public void validateNumberOfFloors(int numOfFloors) throws FloorServiceException {
        if (numOfFloors < 8 || numOfFloors > 17)
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
