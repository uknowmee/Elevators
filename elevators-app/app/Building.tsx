import ElevatorShaft from "./ElevatorShaft";
import Floor from "./Floor";

type BuildingProps = {
    numOfFloors: number;
    numOfElevators: number;
};

const Building = ({ numOfFloors, numOfElevators }: BuildingProps) => {
    const elevatorShafts = [];
    const floors = [];

    for (let i = 0; i < numOfElevators; i++) {
        elevatorShafts.push(<ElevatorShaft key={i} numOfFloors={numOfFloors} />);
    }

    for (let i = 0; i < numOfFloors + 1; i++) {
        floors.push(<Floor key={i} number={i} />);
    }
    floors.reverse();

    return (
        <div style={{ display: "flex", gap: "20px", height: "100vh", overflow: "hidden", flexDirection: "row", margin: "20px"}}>
            <div style={{ display: "flex", flexDirection: "column" }}>
                {floors}
            </div>
            {elevatorShafts}
        </div>
    );
};

export default Building;