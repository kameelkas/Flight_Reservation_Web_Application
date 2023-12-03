import React, { useState, useEffect } from "react";
import Modal from "react-modal";
import "./Payment.css";

function GuestPayment({ onPaymentSubmit, hasInsurance, seatID, flightID }) {
  const [cardNumber, setCardNumber] = useState("");
  const [expiry, setExpiry] = useState("");
  const [cvv, setCvv] = useState("");
  const [isPaymentSuccessful, setPaymentSuccessful] = useState(false);
  const [seatPrice, setSeatPrice] = useState(0);
  const [flightPrice, setFlightPrice] = useState(0);
  const [subtotal, setSubtotal] = useState(0);
  const [totalAmnt, setTotalAmnt] = useState(0);
  const [ticketID, setTicketID] = useState(0);
  const [passengerName, setPassengerName] = useState("");
  const [passengerEmail, setPassengerEmail] = useState("");
  const [passengerPhone, setPassengerPhone] = useState("");
  const [isEmailValid, setIsEmailValid] = useState(true);

  const validateEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const handleEmailChange = (e) => {
    const enteredEmail = e.target.value;
    setPassengerEmail(enteredEmail);
    setIsEmailValid(validateEmail(enteredEmail));
  };

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
  }, []);

  useEffect(() => {
    const calculateAmount = subtotal * 1.05;
    console.log(calculateAmount);
    setTotalAmnt(calculateAmount);
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

  const sendPassengerInfo = async () => {
    const response = await fetch(
      "http://localhost:8080/FlightApp/Customer/Guest/Create",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: passengerName,
          emailAddr: passengerEmail,
          phoneNum: passengerPhone,
          customerPassword: null,
        }),
      }
    );

    // const result = await response.json();
    // console.log("Customer Creation Return:", result);
  };

  const sendCustomerFlightDetails = async () => {
    await sendPassengerInfo();

    const getTicketID = await fetch(
      `http://localhost:8080/FlightApp/Ticket/Create/${passengerEmail}/${flightID}/${seatID}/${totalAmnt}/false/${hasInsurance}`,
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
    setPassengerName("");
    setPassengerEmail("");
    setPassengerPhone("");
    setIsEmailValid(true);
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
          <b>Total Amount: ${totalAmnt}</b>
        </div>
      </div>
      <form onSubmit={handleSubmit}>
        <label htmlFor="passengerName">Passenger Name</label>
        <input
          type="text"
          id="passengerName"
          value={passengerName}
          onChange={(e) => setPassengerName(e.target.value)}
        />

        <label htmlFor="passengerEmail">Passenger Email</label>
        <input
          type="email"
          id="passengerEmail"
          value={passengerEmail}
          onChange={handleEmailChange}
        />
        {!isEmailValid && (
          <p className="error-message">Please enter a valid email address.</p>
        )}

        <label htmlFor="passengerPhone">Passenger Phone</label>
        <input
          type="tel"
          id="passengerPhone"
          value={passengerPhone}
          onChange={(e) => setPassengerPhone(e.target.value)}
        />

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

export default GuestPayment;
