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
      case ElevatorState.STOPPED: return "stopped";
      case ElevatorState.MOVING: return "moving";
      case ElevatorState.OPENING: return "opening";
      case ElevatorState.CLOSING: return "closing";
      case ElevatorState.ENTERING_EXITING: return "in/out";
      default: return "";
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center", width: "65px"}}>
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
