import React, { use, useState } from "react";
import ElevatorShaft from "./ElevatorShaft";
import Floor from "./Floor";
import "../styles/Building.css"
import FloorForm from "./FloorForm";

import { useSimulationStore } from "../stores/customers";

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

    const elevatorShafts = Array.from({ length: numOfElevators }, (_, index) => (
        <ElevatorShaft key={index} numOfFloors={numOfFloors} id={index} />
    ));

    const floors = Array.from({ length: numOfFloors + 1 }, (_, index) => (
        <div style={{ display: "flex", flexDirection: "row", justifyContent: "center", alignItems: "center" }}>
            <div className="floor-button" onClick={() => toggleShowForm(index)}>
                {showForm ? " " : "+"}
            </div>
            <Floor key={index} number={index} names={tmp(index)} />
        </div>
    )).reverse();

    function tmp(index: number): string[] {
        const floor = useSimulationStore.getState().floors[index];
        if (!floor || !floor.people || floor.people.length === 0) {
          return [];
        }
        return floor.people.map((person) =>
          person.name + " " + person.direction + " " + person.desiredFloorNumber
        );
    }  

    return (
        <div className="building-wrapper">
            <div className="building">
                <div style={{ width: "80px" }}></div>
                <div style={{ display: "flex", flexDirection: "column", paddingRight: "30px" }}>
                    {floors}
                </div>
                {elevatorShafts}
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
