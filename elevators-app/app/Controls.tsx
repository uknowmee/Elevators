import React from "react";

type NavigationProps = {
    onReset: () => void;
    onStep: () => void;
};

const Controls = ({ onReset, onStep }: NavigationProps) => {

    return (
        <div style={{ display: "flex", margin: "10px", flexDirection: "column"}}>
            <button onClick={onReset}>Reset</button>
            <button onClick={onStep}>Step</button>
        </div>
    );
};

export default Controls;