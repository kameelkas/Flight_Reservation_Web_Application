import React, { useState, useEffect } from "react";
import Modal from "react-modal";
import "./Payment.css";

function Payment({ onPaymentSubmit, hasInsurance, seatID, flightID }) {
  const [cardNumber, setCardNumber] = useState("");
  const [expiryMonth, setExpiryMonth] = useState("");
  const [expiryYear, setExpiryYear] = useState("");
  const [cvv, setCvv] = useState("");
  const [isPaymentSuccessful, setPaymentSuccessful] = useState(false);
  const [seatPrice, setSeatPrice] = useState(0);
  const [flightPrice, setFlightPrice] = useState(0);
  const [subtotal, setSubtotal] = useState(0);
  const [totalAmnt, setTotalAmnt] = useState(0);
  const formattedNumber = parseFloat(totalAmnt.toFixed(2)).toString();


  useEffect(() => {
    const getPriceDetails = async () => {
      const recieveSeatPrice = await fetch(
        `http://localhost:8080/FlightApp/Seat/GetPriceBySeatID/${seatID}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        })

        const gottenSeatPrice = await recieveSeatPrice.json();
        setSeatPrice(gottenSeatPrice);

        const recieveFlightPrice = await fetch(
          `http://localhost:8080/FlightApp/Flight/GetFlightPriceUsingFlightID/${flightID}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          })
  
          const gottenFlightPrice = await recieveFlightPrice.json();
          setFlightPrice(gottenFlightPrice);

          calculateSubtotal();
    };
    getPriceDetails();
  }, []);

  const calculateSubtotal = () => {
    if(hasInsurance){
      setSubtotal(seatPrice + flightPrice + 50);
    } else {
      setSubtotal(seatPrice + flightPrice);    
    }
    const calculateDiscount = (subtotal*1.05) - ((subtotal*1.05)*0.2)
    setTotalAmnt(calculateDiscount)
  };


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
      window.alert(
        "One or more entered fields was incorrect. Please try again."
      );
      return;
    }

    //SEND successive POST's to backend

    setPaymentSuccessful(true);
  };

  const handlePaymentSuccessClose = () => {
    setPaymentSuccessful(false);
    const expiryDate = expiryMonth + "/" + expiryYear; // Combine month and year
    onPaymentSubmit({ cardNumber, expiryDate, cvv });
  };

  return (
    <div className="payment-form">
      <h2>Payment Details</h2>
      <div id="priceDetails">
        <h3>Payment Amount Breakdown</h3>
        <div>Seat Price: {seatPrice}</div>
        <div>Flight Price: {flightPrice}</div>
        {hasInsurance ? (
          <div>Insurance Price: $50.00</div>
        ) : (
          <div>Insurance Price - Skipped: $0.00</div>
        )}
        <div>Subtotal: {subtotal}</div>
        <div>Tax: {subtotal * 0.05}</div>
        <div>Since you're a registed member, we are offering you a 20% discount!</div>
        <div>
          <b>Total Amount: {formattedNumber}</b>
        </div>
      </div>
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
