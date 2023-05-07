"use client";

import React, { useState } from "react";
import BuildingForm from "./BuildingForm"
import Building from "./Building"
import Controls from "./Controls";


export default function Home() {
  const initFloors: number = 8;
  const initElevators: number = 3;

  const [showForm, setShowForm] = useState(true);
  const [numOfFloors, setNumOfFloors] = useState(initFloors);
  const [numOfElevators, setNumOfElevators] = useState(initElevators);

  const handleFormSubmit = (floors: number, elevators: number) => {
    // TODO: POST /api/init
    setNumOfFloors(floors);
    setNumOfElevators(elevators);
    setShowForm(false);
  };

  const resetForm = () => {
    // TODO: DELETE /api/clear
    console.log("Reset");

    setShowForm(true);
    setNumOfFloors(initFloors);
    setNumOfElevators(initElevators);
  };

  const step = () => {
    // TODO: PUT /api/step
    // TODO: GET /api/state
    console.log("Step");
  };

  return (
    <main>
      {showForm ? (
        <div style={{ display: "flex", justifyContent: "center" }}>
          <BuildingForm onSubmit={handleFormSubmit} initFloors={initFloors} initElevators={initElevators} />
        </div>
      ) : (
        <div style={{ display: "flex", justifyContent: "center", paddingTop: "20px"}}>
          <Building numOfFloors={numOfFloors} numOfElevators={numOfElevators} />
          <Controls onReset={resetForm} onStep={step} />
        </div>
      )}
    </main>
  );
}
