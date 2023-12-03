import React, { useState, useEffect } from "react";
import Modal from "react-modal";
import "./Payment.css";

function Payment({ onPaymentSubmit, hasInsurance, seatID, flightID, email }) {
  const [cardNumber, setCardNumber] = useState("");
  const [expiry, setExpiry] = useState("");
  const [cvv, setCvv] = useState("");
  const [isPaymentSuccessful, setPaymentSuccessful] = useState(false);
  const [seatPrice, setSeatPrice] = useState(0);
  const [flightPrice, setFlightPrice] = useState(0);
  const [subtotal, setSubtotal] = useState(0);
  const [totalAmnt, setTotalAmnt] = useState(0);
  const [ticketID, setTicketID] = useState(0);

  useEffect(() => {
    const getPriceDetails = async () => {
      const recieveSeatPrice = await fetch(
        `http://localhost:8080/FlightApp/Seat/GetPriceBySeatID/${seatID}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      const gottenSeatPrice = await recieveSeatPrice.json();
      setSeatPrice(gottenSeatPrice);

      const recieveFlightPrice = await fetch(
        `http://localhost:8080/FlightApp/Flight/GetFlightPriceUsingFlightID/${flightID}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      const gottenFlightPrice = await recieveFlightPrice.json();
      setFlightPrice(gottenFlightPrice);
    };

    getPriceDetails();
    console.log(email);
  }, []);

  useEffect(() => {
    const calculateDiscount = subtotal * 1.05 - subtotal * 1.05 * 0.2;
    console.log(calculateDiscount);
    setTotalAmnt(calculateDiscount);
    console.log(totalAmnt);
  }, [subtotal]);

  useEffect(() => {
    calculateSubtotal();
  }, [seatPrice, flightPrice, hasInsurance]);

  const calculateSubtotal = () => {
    if (hasInsurance) {
      setSubtotal(seatPrice + flightPrice + 50);
    } else {
      setSubtotal(seatPrice + flightPrice);
    }
  };

  const sendCustomerFlightDetails = async () => {
    const getTicketID = await fetch(
      `http://localhost:8080/FlightApp/Ticket/Create/${email}/${flightID}/${seatID}/${totalAmnt}/false/${hasInsurance}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    const gottenTicketID = await getTicketID.json();
    setTicketID(gottenTicketID);
    console.log("FromBACK: ", gottenTicketID);
    console.log("TicketID: ", ticketID);
  };

  useEffect(() => {
    sendPaymentDetails();
  }, [ticketID]);

  const sendPaymentDetails = () => {
    fetch(
      `http://localhost:8080/FlightApp/Payment/Create/${cardNumber}/${expiry}/${cvv}/${totalAmnt}/${ticketID}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await sendCustomerFlightDetails();
    //SEND successive POST's to backend
    // sendCustomerFlightDetails();
    setPaymentSuccessful(true);
  };

  const handlePaymentSuccessClose = () => {
    setPaymentSuccessful(false);
    resetAllFields();
    onPaymentSubmit({ cardNumber, expiry, cvv });
  };

  const resetAllFields = () => {
    setCardNumber("");
    setExpiry("");
    setCvv("");
    setPaymentSuccessful(false);
    setSeatPrice(0);
    setFlightPrice(0);
    setSubtotal(0);
    setTotalAmnt(0);
    setTicketID(0);
  };

  return (
    <div className="payment-form">
      <h2>Payment Details</h2>
      <div id="priceDetails">
        <h3>Payment Amount Breakdown</h3>
        <div>Seat Price: ${seatPrice}</div>
        <div>Flight Price: ${flightPrice}</div>
        {hasInsurance ? (
          <div>Insurance Price: $50</div>
        ) : (
          <div>Insurance Price - Skipped: $0</div>
        )}
        <div>Subtotal: ${subtotal}</div>
        <div>Tax: ${subtotal * 0.05}</div>
        <div>
          Since you're a registed member, we are offering you a 20% discount!
        </div>
        <div>
          <b>Total Amount: ${totalAmnt}</b>
        </div>
      </div>
      <form onSubmit={handleSubmit}>
        <label htmlFor="cardNumber">Card Number</label>
        <input
          type="text"
          id="cardNumber"
          pattern="\d{16}"
          value={cardNumber}
          onChange={(e) => setCardNumber(e.target.value)}
        />
        <div className="expiry-fields">
          <label htmlFor="expiry">Expiry Year</label>
          <input
            type="text"
            id="expiry"
            value={expiry}
            pattern="\d{4}"
            onChange={(e) => setExpiry(e.target.value)}
            maxLength="4" // Limit input to 4 characters
            placeholder="MMYY"
          />
        </div>
        <label htmlFor="cvv">CVV</label>
        <input
          type="text"
          id="cvv"
          value={cvv}
          pattern="\d{3}"
          onChange={(e) => setCvv(e.target.value)}
          maxLength="3" // Limit input to 3 characters
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
          <button onClick={handlePaymentSuccessClose}>OK!</button>
        </div>
      </Modal>
    </div>
  );
}

export default Payment;
