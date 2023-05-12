"use client";

import React, { use, useState } from "react";
import BuildingForm from "./BuildingForm"
import Building from "./building/Building"
import Controls from "./Controls";

import { useSimulationStore } from "../stores/customers";



export default function Home() {
  const initFloors: number = 8;
  const initElevators: number = 3;

  const [showForm, setShowForm] = useState(true);
  const [showControls, setshowControls] = useState(true);
  const [numOfFloors, setNumOfFloors] = useState(initFloors);
  const [numOfElevators, setNumOfElevators] = useState(initElevators);

  const handleFormSubmit = (floors: number, elevators: number) => {
    useSimulationStore.getState().InitSimulation(floors, elevators);
    setNumOfFloors(floors);
    setNumOfElevators(elevators);
    setShowForm(false);
  };

  const resetForm = () => {
    useSimulationStore.getState().StopSimulation();
    setShowForm(true);
    setNumOfFloors(initFloors);
    setNumOfElevators(initElevators);
  };

  const step = () => {
    useSimulationStore.getState().MakeSimulationStep();
  };

  return (
    <main>
      {showForm ? (
        <div style={{ display: "flex", justifyContent: "center" }}>
          <BuildingForm handleSubmit={handleFormSubmit} initFloors={initFloors} initElevators={initElevators} />
        </div>
      ) : (
        <div style={{ display: "flex", justifyContent: "center", paddingTop: "20px", overflow: "hidden"}}>
          <Building numOfFloors={numOfFloors} numOfElevators={numOfElevators} setShowControls={setshowControls} />
          <Controls onReset={resetForm} onStep={step} renderButtons={showControls} />
        </div>
      )}
    </main>
  );
}
