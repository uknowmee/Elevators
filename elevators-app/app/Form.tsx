import React, { useState } from "react";
import "../styles/Form.css"
import { init } from "next/dist/compiled/@vercel/og/satori";

type FormProps = {
    onSubmit: (numOfFloors: number, numOfElevators: number) => void;
    initFloors: number;
    initElevators: number;
};

const Form = ({ onSubmit, initElevators, initFloors}: FormProps) => {
    const maxFloors: number = 15;
    const maxElevators: number = 16;

    const [numOfFloors, setNumOfFloors] = useState(initFloors);
    const [numOfElevators, setNumOfElevators] = useState(initElevators);

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        onSubmit(numOfFloors, numOfElevators);
    };

    return (
        <div className="form-container">
            <form onSubmit={handleSubmit}>
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

export default Form;