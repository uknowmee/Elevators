import React, { useState, useEffect } from "react";
import "../styles/Floor.css";

type FloorProps = {
    number: number;
    names: string[];
};

const Floor = ({ number, names }: FloorProps) => {
    const [showNames, setShowNames] = useState(false);
    const [showingNames, setShowingNames] = useState(false);

    useEffect(() => {
        if (showNames) {
            setShowingNames(true);
            setTimeout(() => {
                setShowingNames(false);
                setShowNames(false);
            }, 2000);
        }
    }, [showNames]);

    const toggleShowNames = () => {
        setShowNames(!showNames);
    };

    return (
        <div className="floor" onClick={toggleShowNames}>
            <div className={`floor-number ${showNames ? "hidden" : ""} ${names?.length > 0 ? "has-names" : "no-names"}`}>
                {number}
            </div>
            <div
                className={`floor-names ${showingNames ? "visible" : ""}`}
                onAnimationEnd={() => setShowingNames(false)}
            >
                <div className="floor-names-inner">
                    {names?.map((name, index) => (
                        <div key={index} className="name-item">
                            {index + 1}. {name}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Floor;
