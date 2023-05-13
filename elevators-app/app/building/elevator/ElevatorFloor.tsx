import React, { useState, useEffect } from "react";
import "../../../styles/elevatorFloor.css"
import { ElevatorState } from "../../../stores/customers";

type ElevatorFloorProps = {
  isCurrent: boolean;
  number: number;
  information: string[];
  destinationFloor: number;
  state: ElevatorState | null;
};

function ElevatorFloor({ number, isCurrent, information, destinationFloor, state }: ElevatorFloorProps) {
  const color = isCurrent ? "green" : "#ddd";

  const [showNames, setShowNames] = useState(false);
  const [showingNames, setShowingNames] = useState(false);

  useEffect(() => {
    if (showNames) {
      setShowingNames(true);
      setTimeout(() => {
        setShowingNames(false);
        setShowNames(false);
      }, 2000);
    }
  }, [showNames]);

  const toggleShowNames = () => {
    setShowNames(!showNames);
  };

  return !isCurrent
    ? (
      <div className="elevatorFloor" style={{ backgroundColor: color }}>
        <div style={{ fontSize: "24px", fontWeight: "bold" }}>{number}</div>
      </div>
    )
    : (
      <div className="elevatorFloor" onClick={toggleShowNames}>
        <div className={`${information.length > 0 ? "has-names" : "no-names"}`}>
          {destinationFloor}
        </div>
        <div
          className={`elevatorFloor-names ${showingNames ? "visible" : ""}`}
          onAnimationEnd={() => setShowingNames(false)}
        >
          <div
            className="elevatorFloor-names-inner"
            style={{ overflow: "auto" }}
          >
            {state}
            {information.map((line, index) => (
              <div key={index} className="name-item">
                {index + 1}. {line}
              </div>
            ))}
          </div>
        </div>
      </div>
    );
};

export default ElevatorFloor;
