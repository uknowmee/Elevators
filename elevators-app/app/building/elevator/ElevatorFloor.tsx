import React, { useState, useEffect } from "react";
import "../../../styles/elevatorFloor.css"

type ElevatorFloorProps = {
  isCurrent: boolean;
  number: number;
  names: string[];
};

function ElevatorFloor({ number, isCurrent, names }: ElevatorFloorProps) {
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
        <div className={`${names.length > 0 ? "has-names" : "no-names"}`}>{number}</div>
        <div
          className={`elevatorFloor-names ${showingNames ? "visible" : ""}`}
          onAnimationEnd={() => setShowingNames(false)}
        >
          <div
            className="elevatorFloor-names-inner"
            style={{ overflow: "auto" }}
          >
            {names.map((name, index) => (
              <div key={index} className="name-item">
                {index + 1}. {name}
              </div>
            ))}
          </div>
        </div>
      </div>
    );
};

export default ElevatorFloor;
