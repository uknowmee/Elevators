import React from "react";
import Floor from "./Floor";
import { useSimulationStore } from "../stores/customers";

type FloorsProps = {
    numOfFloors: number;
    toggleShowForm: (index: number | null) => void;
    showForm: boolean;
};

function Floors({ numOfFloors, toggleShowForm, showForm }: FloorsProps) {

    const floors = useSimulationStore(state => state.floors);
    const people = floors.map(floor => floor.people);

    return (
        <div>
            {Array.from({ length: numOfFloors + 1 }, (_, index) => (
                <div style={{ display: "flex", flexDirection: "row", justifyContent: "center", alignItems: "center" }}>
                    <div className="floor-button" onClick={() => toggleShowForm(index)}>
                        {showForm ? " " : "+"}
                    </div>
                    <Floor key={index} number={index} names={
                        people[index]?.map((person) => person.name + " " + person.direction + " " + person.desiredFloorNumber)
                    } />
                </div>
            )).reverse()}
        </div>
    );
}

export default Floors;