import React from "react";
import "./Insurance.css";

function Insurance({ onInsuranceSubmit }) {
  const handleInsuranceSubmit = () => {
    // Implement your logic for insurance submission
    onInsuranceSubmit();
  };

  return (
    <div>
      <div className="flex-container">
        <div className="insurance-container">
          <h2>Purchase an Insurance Plan</h2>
          <br></br>
          <div>
            - Cancel or change flight for no additional fees
            <br></br>- 1 free checked bag
            <br></br>- Full refund upon cancellation
            <br></br>
            <br></br>
            <button>Select</button>
          </div>
        </div>
      </div>
      <br></br>
      <br></br>
      <button onClick={handleInsuranceSubmit}>Skip Insurance and Pay</button>
    </div>
  );
}

export default Insurance;
