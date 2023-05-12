import React, { useState } from "react";

type FloorFormProps = {
    floorIndex: number;
    maxFloor: number;
    handleClose: () => void;
    handleSubmit: (name: string, startFloor: number, desiredFloor: number) => void;
};

function FloorForm({ floorIndex, maxFloor, handleClose, handleSubmit }: FloorFormProps) {

    const [name, setName] = useState("");
    const [desiredFloor, setDesiredFloor] = useState(0);

    const onSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        handleSubmit(name, floorIndex, desiredFloor);
    };

    return (
        <div className="floor-form">
            <form onSubmit={onSubmit}>
                <label>Floor {floorIndex}</label>
                <label>Name:
                    <input
                        type="text"
                        id="name-input"
                        name="name"
                        value={name}
                        required={true}
                        minLength={3}
                        maxLength={20}
                        onChange={(e) => setName(e.target.value)}
                    />
                </label>
                <br />
                <label>Desired Floor:
                    <input
                        type="number"
                        id="desired-floor-input"
                        name="desired-floor"
                        value={desiredFloor}
                        min={0}
                        max={maxFloor}
                        onChange={(e) => setDesiredFloor(parseInt(e.target.value))}
                    />
                </label>
                <br />
                <div style={{ display: "flex", flexDirection: "row", gap: "10px" }}>
                    <button type="button" onClick={handleClose}>Close</button>
                    <button type="submit">Submit</button>
                </div>
            </form>
        </div>
    );
}

export default FloorForm;
