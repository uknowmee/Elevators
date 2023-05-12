import React, { useState, useEffect } from "react";
import "../../../styles/elevatorFloor.css"

type ElevatorFloorProps = {
  isCurrent: boolean;
  number: number;
  information: string[];
  isOpened: boolean;
  destinationFloor: number;
  state: string;
};

function ElevatorFloor({ number, isCurrent, information, isOpened, destinationFloor, state }: ElevatorFloorProps) {
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
        <div
          className={`${information.length > 0 ? "has-names" : "no-names"}`}
          style={ isOpened ? {border: "2px solid black"} : {}}
        >
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
