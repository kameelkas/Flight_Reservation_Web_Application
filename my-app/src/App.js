import React, { useState } from "react";
import "./App.css";

function App() {
  const [selectedOption, setSelectedOption] = useState(null);

  const handleButtonClick = (option) => {
    setSelectedOption(option);
  };

  return (
    <div className="container ">
      <div className="header">
        <div id="title-holder">
          <h2>Flight Booker</h2>
        </div>
      </div>
      <div className="main text-center mt-5">
        <h4>Continue as </h4>
        {selectedOption === null && (
          <div className="d-grid gap-2 col-6 mx-auto">
            <button
              className="btn btn-primary"
              type="button"
              onClick={() => handleButtonClick("Ticket Booker")}
            >
              Ticket Booker
            </button>
            <button
              className="btn btn-primary"
              type="button"
              onClick={() => handleButtonClick("Crew")}
            >
              Crew
            </button>
            <button
              className="btn btn-primary"
              type="button"
              onClick={() => handleButtonClick("Third Option")}
            >
              Third Option
            </button>
          </div>
        )}
        {selectedOption === "Ticket Booker" && (
          <div className="d-grid gap-2 col-6 mx-auto">
            <button
              className="btn btn-primary"
              type="button"
              onClick={() => handleButtonClick("Travel Agent")}
            >
              Travel Agent
            </button>
            <button
              className="btn btn-primary"
              type="button"
              onClick={() => handleButtonClick("Customer")}
            >
              Customer
            </button>
          </div>
        )}
        {selectedOption === "Customer" && (
          <div className="d-grid gap-2 col-6 mx-auto">
            <button className="btn btn-primary" type="button">
              Member
            </button>
            <button className="btn btn-primary" type="button">
              Guest
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
