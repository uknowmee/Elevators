"use client";

import React, { use, useState } from "react";
import ElevatorFloor from "./ElevatorFloor";

import { ElevatorState, useSimulationStore } from "../../../stores/customers";

type ElevatorShaftProps = {
  numOfFloors: number;
  id: number;
};

function ElevatorShaft({ numOfFloors, id }: ElevatorShaftProps) {
  const elevator = useSimulationStore(state => state.elevators).find(elevator => elevator.serialNumber === id);
  const info = (): String => {
    switch (elevator?.state) {
      case ElevatorState.STOPPED: return "stop";
      case ElevatorState.MOVING: return "move";
      case ElevatorState.OPENING: return "open";
      case ElevatorState.CLOSING: return "close";
      case ElevatorState.ENTERING_EXITING: return "in/out";
      default: return "";
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center", }}>
      {
        Array.from(
          { length: numOfFloors + 1 }, (_, index) => (
            <ElevatorFloor
              key={index}
              number={index}
              isCurrent={index === elevator?.currentFloor}
              information={elevator?.people?.map((person) => person.name + " " + person.desiredFloorNumber) ?? []}
              destinationFloor={elevator?.destinationFloor ?? elevator?.currentFloor ?? 0}
              state={elevator?.state ?? null}
            />
          )
        ).reverse()
      }
      <div style={{zIndex:2, color: "black", fontSize: "14px", bottom: "-10px"}}>
        {info()}
      </div>
    </div>
  );
}

export default ElevatorShaft;
