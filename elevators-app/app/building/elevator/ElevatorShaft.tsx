"use client";

import React, { use, useState } from "react";
import ElevatorFloor from "./ElevatorFloor";

import { useSimulationStore } from "../../../stores/customers";

type ElevatorShaftProps = {
  numOfFloors: number;
  id: number;
};

function ElevatorShaft({ numOfFloors, id }: ElevatorShaftProps) {
  const elevator = useSimulationStore(state => state.elevators).find(elevator => elevator.serialNumber === id);

  return (
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center", }}>
      {
        Array.from(
          { length: numOfFloors + 1 }, (_, index) => (
            <ElevatorFloor
              key={index}
              number={index}
              isCurrent={index === elevator?.currentFloor}
              names={elevator?.people?.map((person) => person.name + " " + person.desiredFloorNumber) ?? []}
            />
          )
        ).reverse()
      }
    </div>
  );
}

export default ElevatorShaft;
