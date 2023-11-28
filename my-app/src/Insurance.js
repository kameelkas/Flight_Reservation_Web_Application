import React from "react";
import "./Insurance.css";

function Insurance({ onInsuranceSubmit }) {
  const handleInsuranceSubmit = () => {
    // Implement your logic for insurance submission
    onInsuranceSubmit();
  };

  return (
    <div className="insurance-selection">
      <div>
        <h2>Insurance Selection</h2>
        <br></br>
        <div className="flex-container">
          <div className="left-div">
            <h4>Fully Covered</h4>
            <br></br>
            <div>
              - Cancel or change flight for no additional fees
              <br></br>- 1 free checked bag
              <br></br>- Free seat selection
              <br></br>- Fully refundable
              <br></br>
              <br></br>
              <button>Select</button>
            </div>
          </div>
          <div className="right-div">
            <h4>Partially Covered</h4>
            <br></br>
            <div>
              - Cancel or change flight for a fee
              <br></br>- Checked bag for a fee
              <br></br>- Seat selection for a fee
              <br></br>- Partially refundable
              <br></br>
              <br></br>
              <button>Select</button>
            </div>
          </div>
        </div>
        <br></br>
      </div>
      <button onClick={handleInsuranceSubmit}>Skip Insurance and Pay</button>
    </div>
  );
}

export default Insurance;
