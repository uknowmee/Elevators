"use client";

import React, { useState } from "react";
import Form from "./Form"
import Building from "./Building"
import Navigation from "./Navigation";


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
    setShowForm(true);
    setNumOfFloors(initFloors);
    setNumOfElevators(initElevators);
  };

  const step = (value: number) => {
    console.log(value);
  };

  return (
    <main>
      {showForm ? (
        <div style={{ display: "flex", justifyContent: "center" }}>
          <Form onSubmit={handleFormSubmit} initFloors={initFloors} initElevators={initElevators} />
        </div>
      ) : (
        <div style={{ display: "flex", justifyContent: "center" }}>
          <Building numOfFloors={numOfFloors} numOfElevators={numOfElevators} />
          <Navigation onReset={resetForm} onStep={step} />
        </div>
      )}
    </main>
  );
}
