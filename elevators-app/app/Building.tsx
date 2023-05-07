import ElevatorShaft from "./ElevatorShaft";
import Floor from "./Floor";

type BuildingProps = {
    numOfFloors: number;
    numOfElevators: number;
};

const Building = ({ numOfFloors, numOfElevators }: BuildingProps) => {

    const elevatorShafts = Array.from({ length: numOfElevators }, (_, index) => (
        <ElevatorShaft key={index} numOfFloors={numOfFloors} />
    ));

    const floors = Array.from({ length: numOfFloors + 1 }, (_, index) => (
        <Floor key={index} number={index} names={["test", "imie", "asdasd", "asdasd"]} />
    )).reverse();

    return (
        <div style={{ display: "flex", height: "100vh", overflow: "hidden", flexDirection: "row", margin: "3px" }}>
            <div style={{ width: "100px"}}></div>
            <div style={{ display: "flex", flexDirection: "column", paddingRight: "30px"}}>
                {floors}
            </div>
            {elevatorShafts}
            <div style={{ width: "100px"}}></div>
        </div>
    );
};

export default Building;