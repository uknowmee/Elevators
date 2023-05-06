import React from "react";

type NavigationProps = {
    onReset: () => void;
    onStep: (value: number) => void;
};

const Navigation = ({ onReset, onStep }: NavigationProps) => {

    function step() {
        console.log("Step");
        // TODO: PUT /api/step
        // TODO: GET /api/state
        onStep(666);
    }

    function reset() {
        console.log("Reset");
        // TODO: DELETE /api/clear
        onReset();
    }

    return (
        <div style={{ display: "flex", margin: "10px", flexDirection: "column"}}>
            <button onClick={reset}>Reset</button>
            <button onClick={step}>Step</button>
        </div>
    );
};

export default Navigation;