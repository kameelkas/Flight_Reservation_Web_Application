import React, { useState, useEffect } from "react";
import "./App.css";
import SeatSelection from "./SeatSelection";
import Insurance from "./Insurance";
import Payment from "./Payment";

function App() {
  const [selectedOption, setSelectedOption] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loggedInUser, setLoggedInUser] = useState(null);
  const [showTicketPurchaseForm, setShowTicketPurchaseForm] = useState(false);
  const [departureDate, setDepartureDate] = useState("");
  //const [destination, setDestination] = useState("");
  const [origin, setOrigin] = useState("");
  const [ticketId, setTicketId] = useState("");
  const [role, setRole] = useState(""); // State to store the selected role
  const [showSeatSelection, setShowSeatSelection] = useState(false);
  const [showPayment, setShowPayment] = useState(false);
  const [showInsurance, setShowInsurance] = useState(false);
  const [destOptions, setDestOptions] = useState([]);
  const [selectedDest, setSelectedDest] = useState("");
  const [availableFlights, setAvailableFlights] = useState([]);
  const [showSearchFlight, setShowSearchFlight] = useState(false);

  const handleButtonClick = (option) => {
    const lowerCaseOption = option.toLowerCase();
    setSelectedOption(lowerCaseOption);
    if (lowerCaseOption === "member") {
      setShowModal(true);
      setShowTicketPurchaseForm(false);
    } else if (lowerCaseOption === "guest") {
      // Directly show ticket purchase options for guests
      setShowTicketPurchaseForm(true);
      setShowModal(false);
      setSelectedOption("purchase ticket"); // Ensure this is set for guests
    } else if (lowerCaseOption === "purchase ticket") {
      setShowTicketPurchaseForm(true);
      setShowModal(false);
    } else if (lowerCaseOption === "cancel ticket") {
      setShowTicketPurchaseForm(false);
      setShowModal(false);
      setSelectedOption(lowerCaseOption);
    }

    getAllDestinations();
  };

  useEffect(() => {
    getAllDestinations();
  }, []);

  const getAllDestinations = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/FlightApp/Flight/GetAllDestinations`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const destList = await response.json();
      setDestOptions(destList); // Update state here
    } catch (error) {
      console.error("There was a problem with the fetch operation:", error);
    }
  };

  const getSeatPrice = async () => {
    const recieve = await fetch(
      `http://localhost:8080/FlightApp/Ticket/GetPrice/2`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    const seatPrice = await recieve.json();
    console.log(seatPrice);
  };

  const getAllFlightsForLocation = async () => {
    console.log(selectedDest);
    const recieve = await fetch(
      `http://localhost:8080/FlightApp/Flight/GetAllFlightsByDestination/${selectedDest}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    const allFlightsOfLocation = await recieve.json();
    setAvailableFlights(allFlightsOfLocation);
    console.log(allFlightsOfLocation);
  };

  //http://localhost:8080/FlightApp/Flight/GetAllFlightsByDestination/London

  const handleModalClose = () => {
    setShowModal(false);
    setUsername("");
    setPassword("");
    setRole(""); // Reset the role when the modal is closed
  };

  const handleLogin = () => {
    console.log("Username:", username);
    console.log("Password:", password);
    console.log("Role:", role); // Log the selected role
    setLoggedInUser(username);
    handleModalClose();
  };

  const handleTicketPurchase = () => {
    console.log("Destination:", selectedDest);
    getAllFlightsForLocation(); // Fetch and set available flights
    setShowTicketPurchaseForm(false);
    setShowSearchFlight(true); // Show the flight search UI
  };
  

  const handleFlightSelection = (flight) => {
    console.log("Selected flight:", flight);
    // Implement what happens after a flight is selected, e.g., storing flight data
    // and transitioning to the seat selection phase
    setShowSearchFlight(false);
    setShowSeatSelection(true);
  };

  const handleLogout = () => {
    setLoggedInUser(null);
    setSelectedOption(null);
    setShowModal(false);
    setUsername("");
    setPassword("");
    setShowTicketPurchaseForm(false);
    setDepartureDate("");
    setSelectedDest("");
    setOrigin("");
  };

  const handleSeatSelect = (section, row, seat, continueToInsurance) => {
    if (continueToInsurance) {
      setShowSeatSelection(false);
      setShowInsurance(true);
      // Save the selected seat info if necessary
    }
    // ... existing seat selection logic ...
  };
  const handlePaymentSubmit = (paymentDetails) => {
    console.log("Payment Details:", paymentDetails);
    // Handle the payment submission logic here
  };

  const handleInsuranceContinue = () => {
    setShowInsurance(false);
    setShowPayment(true);
  };

  return (
    <div className="container">
      <div className="header">
        <h2 className="title">Flight Booker</h2>
        {loggedInUser && (
          <div className="user-info">
            <span>Logged in as {loggedInUser}</span>
            <button onClick={handleLogout}>Log out</button>
          </div>
        )}
      </div>
      <div className="main text-center mt-5">
        {!loggedInUser && selectedOption === null && (
          <div className="d-grid gap-2 col-6 mx-auto">
            <h4>Continue As</h4>
            <button
              className="btn btn-primary"
              type="button"
              onClick={() => handleButtonClick("member")}
            >
              Member
            </button>
            <button
              className="btn btn-primary"
              type="button"
              onClick={() => handleButtonClick("guest")}
            >
              Guest
            </button>
            <a
              href="#"
              className="underlined-text"
              onClick={() => handleButtonClick("Third Option")}
            >
              Don't have an account? Sign up here
            </a>
          </div>
        )}

        {loggedInUser &&
        !showTicketPurchaseForm &&
        selectedOption !== "cancel ticket" &&
        selectedOption !== "purchase ticket" ? (
          <div className="ticket-options">
            <button
              className="btn btn-primary"
              type="button"
              onClick={() => handleButtonClick("purchase ticket")}
            >
              Purchase Ticket
            </button>
            <button
              className="btn btn-secondary"
              type="button"
              onClick={() => handleButtonClick("cancel ticket")}
            >
              Cancel Ticket
            </button>
          </div>
        ) : null}

        {showModal &&
          (selectedOption === "member" || selectedOption === "Member") && (
            <div className="modal">
              <div className="modal-content">
                <span className="close" onClick={handleModalClose}>
                  &times;
                </span>
                <h2>Member Login</h2>
                <form>
                  <label htmlFor="username">Username</label>
                  <input
                    type="text"
                    id="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                  />
                  <label htmlFor="password">Password</label>
                  <input
                    type="password"
                    id="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                  <div className="input-group mb-3">
                    <label
                      className="input-group-text"
                      htmlFor="inputGroupSelect01"
                    >
                      Login as
                    </label>
                    <select
                      className="form-select"
                      id="inputGroupSelect01"
                      onChange={(e) => setRole(e.target.value)} // Set the role value
                    >
                      <option selected>Choose...</option>
                      <option value="Customer">Customer</option>
                      <option value="Travel Agent">Travel Agent</option>
                      <option value="Crew">Crew</option>
                      <option value="System Admin">System Admin</option>
                    </select>
                  </div>

                  <button type="button" onClick={handleLogin}>
                    Login
                  </button>
                </form>
              </div>
            </div>
          )}

        {selectedOption === "cancel ticket" && (
          <div className="ticket-id-input">
            <label htmlFor="ticketId">Enter your ticket ID</label>
            <input
              type="text"
              id="ticketId"
              value={ticketId}
              onChange={(e) => setTicketId(e.target.value)}
            />
            <button onClick={() => setSelectedOption(null)}>Go back</button>
          </div>
        )}

        {showTicketPurchaseForm && (
          <div className="ticket-purchase-form">
            <h4>Purchase Ticket</h4>
            <form>
              {/* <label htmlFor="departureDate">Departure Date</label>
              <input
                type="date"
                id="departureDate"
                value={departureDate}
                onChange={(e) => setDepartureDate(e.target.value)}
              /> */}
              <label htmlFor="destination">Destination</label>
              {/* {<input
                type="text"
                id="destination"
                value={destination}
                onChange={(e) => setDestination(e.target.value)}
              />} */}

              <select
                className="form-select"
                id="inputGroupSelect01"
                value={selectedDest}
                onChange={(e) => setSelectedDest(e.target.value)}
              >
                <option value="">Choose...</option>
                {destOptions.map((destination, index) => (
                  <option key={index} value={destination}>
                    {destination}
                  </option>
                ))}
              </select>

              {/* <label htmlFor="origin">Origin</label>
              <input
                type="text"
                id="origin"
                value={origin}
                onChange={(e) => setOrigin(e.target.value)}
              /> */}
              <button type="button" onClick={handleTicketPurchase}>
                Next
              </button>
            </form>
          </div>
        )}
        {/* {!showTicketPurchaseForm && selectedOption === "purchase ticket" && (
          <SeatSelection onSeatSelect={handleSeatSelect} />
        )} */}
        {showSearchFlight && (
          <div>
            <h2>Select a Flight</h2>
            {availableFlights.length > 0 ? (
              <div>
                {availableFlights.map((flight, index) => (
                  <div key={index}>
                    <p>
                      Flight: {flight.flight_ID}: {flight.departureCity} {flight.departureCountry} {flight.departureAirport}
                    </p>
                    <p>
                      Departure: {flight.departureDate} {flight.departureTime} - Arrival: {flight.arrivalDate} {flight.arrivalTime}
                    </p>
                    <button onClick={() => handleFlightSelection(flight)}>
                      Select this Flight
                    </button>
                  </div>
                ))}
              </div>
            ) : (
              <p>No flights available for the selected destination.</p>
            )}
          </div>
        )}
        {showSeatSelection &&
          !showTicketPurchaseForm &&
          selectedOption === "purchase ticket" && (
            <SeatSelection onSeatSelect={handleSeatSelect} />
          )}
        {showInsurance && (
          <Insurance onInsuranceSubmit={handleInsuranceContinue} />
        )}
        {showPayment && <Payment onPaymentSubmit={handlePaymentSubmit} />}
      </div>
    </div>
  );
}

export default App;
