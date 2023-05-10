import React from "react";

type NavigationProps = {
    onReset: () => void;
    onStep: () => void;
    renderButtons: boolean;
};

function Controls({ onReset, onStep, renderButtons}: NavigationProps){

    const buttonStyle: React.CSSProperties = {
        visibility: renderButtons ? "visible" : "hidden",
    };

    return (
        <div style={{ display: "flex", margin: "10px", flexDirection: "column" }}>
          <button onClick={onReset} style={buttonStyle}>Reset</button>
          <button onClick={onStep} style={buttonStyle}>Step</button>
        </div>
      );
};

export default Controls;