import React from "react";
import "./Insurance.css";

function Insurance({ onInsuranceSubmit, setInsurance }) {
  const handleInsuranceSubmit = () => {
    onInsuranceSubmit();
  };

  const chosenInsurance = () => {
    setInsurance(true);
    onInsuranceSubmit();
  };

  return (
    <div>
      <div className="flex-container">
        <div className="insurance-container">
          <h2>Purchase an Insurance Plan</h2>
          <h1><b>$50.00</b></h1>
          <br></br>
          <div>
            - Cancel for no additional fees
            <br></br>- 1 free checked bag
            <br></br>- Full refund upon cancellation
            <br></br>
            <br></br>
            <button onClick={chosenInsurance}>Select</button>
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
