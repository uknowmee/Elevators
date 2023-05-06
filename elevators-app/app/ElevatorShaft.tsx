"use client";

import React, { useState } from "react";
import ElevatorFloor from "./ElevatorFloor";

type ElevatorShaftProps = {
  numOfFloors: number;
};

function ElevatorShaft({ numOfFloors }: ElevatorShaftProps) {
  const [currentFloor, setCurrentFloor] = useState(0);

  const elevatorFloors = Array.from({ length: numOfFloors + 1 }, (_, index) => (
    <ElevatorFloor key={index} number={index} isCurrent={index === currentFloor} />
  )).reverse();

  function top() {
    if (currentFloor < numOfFloors) {
      setCurrentFloor(currentFloor + 1);
    }
  }

  function down() {
    if (currentFloor > 0) {
      setCurrentFloor(currentFloor - 1);
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center", }}>
      <div>{elevatorFloors}</div>

      <div style={{ display: "flex", flexDirection: "row", alignItems: "center", margin: "10px", }} >
        <button onClick={top}>↑</button>
        <button onClick={down}>↓</button>
      </div>

    </div>
  );
}

export default ElevatorShaft;
