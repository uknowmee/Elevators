import React, { useState } from "react";
import "../styles/BuildingForm.css"

type BuildingFormProps = {
    handleSubmit: (numOfFloors: number, numOfElevators: number) => void;
    initFloors: number;
    initElevators: number;
};

function BuildingForm({ handleSubmit, initElevators, initFloors}: BuildingFormProps){
    const maxFloors: number = 17;
    const maxElevators: number = 16;

    const [numOfFloors, setNumOfFloors] = useState(initFloors);
    const [numOfElevators, setNumOfElevators] = useState(initElevators);

    const onSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        handleSubmit(numOfFloors, numOfElevators);
    };

    return (
        <div className="form-container">
            <form onSubmit={onSubmit}>
                <label>
                    Number of floors:
                    <input
                        type="number"
                        min={initFloors}
                        max={maxFloors}
                        value={numOfFloors}
                        onChange={(event) =>
                            setNumOfFloors(parseInt(event.target.value))
                        }
                    />
                </label>
                <br />
                <label>
                    Number of elevators:
                    <input
                        type="number"
                        min={initElevators}
                        max={maxElevators}
                        value={numOfElevators}
                        onChange={(event) =>
                            setNumOfElevators(parseInt(event.target.value))
                        }
                    />
                </label>
                <br />
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default BuildingForm;