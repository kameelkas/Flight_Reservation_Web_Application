import React, { useState, useEffect } from "react";
import Modal from "react-modal";
import "./Payment.css";

function Payment({ onPaymentSubmit, hasInsurance, seatID, flightID }) {
  const [cardNumber, setCardNumber] = useState("");
  const [expiryMonth, setExpiryMonth] = useState("");
  const [expiryYear, setExpiryYear] = useState("");
  const [cvv, setCvv] = useState("");
  const [isPaymentSuccessful, setPaymentSuccessful] = useState(false);

  useEffect(() => {
    console.log("Insurance:", hasInsurance);
    console.log("seatID:", seatID);
    console.log("flightID:", flightID);
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (
      cardNumber.length !== 16 ||
      expiryMonth.length !== 2 ||
      expiryYear.length !== 2 ||
      cvv.length !== 3 ||
      !/^\d+$/.test(cardNumber) ||
      !/^\d+$/.test(expiryMonth) ||
      !/^\d+$/.test(expiryYear) ||
      !/^\d+$/.test(cvv)
    ) {
      setCardNumber("");
      setExpiryMonth("");
      setExpiryYear("");
      setCvv("");
      window.alert("One or more entered fields was incorrect. Please try again.")
      return;
    }
    
    setPaymentSuccessful(true);
  };

  const handlePaymentSuccessClose = () => {
    setPaymentSuccessful(false);
    const expiryDate = expiryMonth + "/" + expiryYear; // Combine month and year
    onPaymentSubmit({ cardNumber, expiryDate, cvv });

  }

  return (
    <div className="payment-form">
      <h2>Payment Details</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="cardNumber">Card Number</label>
        <input
          type="text"
          id="cardNumber"
          value={cardNumber}
          onChange={(e) => setCardNumber(e.target.value)}
        />
        <div className="expiry-fields">
          <div>
            <label htmlFor="expiryMonth">Expiry Month</label>
            <input
              type="text"
              id="expiryMonth"
              value={expiryMonth}
              onChange={(e) => setExpiryMonth(e.target.value)}
              maxLength="2" // Limit input to 2 characters
              placeholder="MM"
            />
          </div>
          <div>
            <label htmlFor="expiryYear">Expiry Year</label>
            <input
              type="text"
              id="expiryYear"
              value={expiryYear}
              onChange={(e) => setExpiryYear(e.target.value)}
              maxLength="2" // Limit input to 2 characters
              placeholder="YY"
            />
          </div>
        </div>
        <label htmlFor="cvv">CVV</label>
        <input
          type="text"
          id="cvv"
          value={cvv}
          onChange={(e) => setCvv(e.target.value)}
          maxLength="3" // Limit input to 2 characters
          placeholder="###"
        />
        <button type="submit">Submit Payment</button>
      </form>
      <Modal
        isOpen={isPaymentSuccessful}
        onRequestClose={handlePaymentSuccessClose}
        contentLabel="Payment Success Modal"
      >
        <div>
          <h2>Payment Successful!</h2>
          <p>Check your email for your ticket and receipt.</p>
          <button onClick={handlePaymentSuccessClose}>OK</button>
        </div>
      </Modal>
    </div>
  );
}

export default Payment;