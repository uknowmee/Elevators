import React, { use, useState } from "react";
import FloorForm from "./floor/FloorForm";
import Floors from "./floor/Floors";
import ElevatorShaft from "./elevator/ElevatorShaft";
import "../../styles/building.css";

import { useSimulationStore } from "../../stores/customers";

type BuildingProps = {
    numOfFloors: number;
    numOfElevators: number;
    setShowControls: (showControls: boolean) => void;
};

function Building({ numOfFloors, numOfElevators, setShowControls }: BuildingProps) {
    const [showForm, setShowForm] = useState(false);
    const [selectedFloorIndex, setSelectedFloorIndex] = useState<number | null>(null);

    const toggleShowForm = (index: number | null) => {
        setSelectedFloorIndex(index);
        setShowForm(!showForm);
        setShowControls(showForm)
    };

    const handleFormClose = () => {
        toggleShowForm(null);
    };

    const handleFormSubmit = (name: string, startFloor: number, desiredFloor: number) => {
        toggleShowForm(null);
        useSimulationStore.getState().AddPerson(startFloor, desiredFloor, name);
    };

    return (
        <div className="building-wrapper">
            <div className="building" style={{ display: "flex", flexDirection: "row", gap: "30px" }}>
                <div style={{ width: "80px" }}></div>
                <div style={{ display: "flex", flexDirection: "column", paddingRight: "30px" }}>
                    <Floors
                        key={-1}
                        numOfFloors={numOfFloors}
                        toggleShowForm={toggleShowForm}
                        showForm={showForm}
                    />
                </div>
                {Array.from({ length: numOfElevators }, (_, index) => (
                    <ElevatorShaft key={index} numOfFloors={numOfFloors} id={index} />
                ))}
                <div style={{ width: "50px" }}></div>
            </div>

            {showForm && selectedFloorIndex !== null && (
                <div className="form-wrapper">
                    <FloorForm
                        floorIndex={selectedFloorIndex}
                        maxFloor={numOfFloors}
                        handleClose={handleFormClose}
                        handleSubmit={(name: string, startFloor: number, desiredFloor: number) => {
                            handleFormSubmit(name, startFloor, desiredFloor);
                        }}
                    />
                </div>
            )}
        </div>
    );
};

export default Building;
