import React from "react";

type ElevatorFloorProps = {
  number: number;
  isCurrent: boolean;
};

const ElevatorFloor = ({ number, isCurrent }: ElevatorFloorProps) => {
  const color = isCurrent ? "green" : "#ddd";
  return (
    <div
      style={{
        border: "1px dotted gray",
        width: "40px",
        height: "40px",
        backgroundColor: color,
      }}
    >
      <div style={{ fontSize: "24px", fontWeight: "bold" }}>{number}</div>
    </div>
  );
};
export default ElevatorFloor;
