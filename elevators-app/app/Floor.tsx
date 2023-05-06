import React from "react";

type FloorProps = {
    number: number;
};

const Floor = ({ number }: FloorProps) => {
    const color = "#ddd";
    return (
        <div style={{
            border: "1px dotted gray",
            width: "200px",
            height: "40px",
            backgroundColor: color,
        }}
        >
            <div style={{ fontSize: "24px", fontWeight: "bold" }}>
                {number}
            </div>
        </div>
    );
};

export default Floor;