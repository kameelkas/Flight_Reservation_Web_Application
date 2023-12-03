import React, { useState, useEffect } from "react";
import "./App.css";
import SeatSelection from "./SeatSelection";
import Insurance from "./Insurance";
import Payment from "./Payment";
import GuestPayment from "./GuestPayment";

function App() {
  const [selectedOption, setSelectedOption] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loggedInUser, setLoggedInUser] = useState(null);
  const [showTicketPurchaseForm, setShowTicketPurchaseForm] = useState(false);
  //const [departureDate, setDepartureDate] = useState("");
  //const [destination, setDestination] = useState("");
  //const [origin, setOrigin] = useState("");
  const [ticketId, setTicketId] = useState("");
  //const [role, setRole] = useState(""); // State to store the selected role
  const [showSeatSelection, setShowSeatSelection] = useState(false);
  const [showPayment, setShowPayment] = useState(false);
  const [showInsurance, setShowInsurance] = useState(false);
  const [destOptions, setDestOptions] = useState([]);
  const [selectedDest, setSelectedDest] = useState("");
  const [availableFlights, setAvailableFlights] = useState([]);
  const [showSearchFlight, setShowSearchFlight] = useState(false);
  const [sendFlightID, setSendFlightID] = useState(0);
  const [sendSeatID, setSendSeatID] = useState(0);
  const [signupUsername, setSignupUsername] = useState("");
  const [signupEmail, setSignupEmail] = useState("");
  const [emailError, setEmailError] = useState("");
  const [signupPhone, setSignupPhone] = useState("");
  const [signupPassword, setSignupPassword] = useState("");
  const [signupAddress, setSignupAddress] = useState("");
  const [signupPostalCode, setSignupPostalCode] = useState("");
  const [signupCity, setSignupCity] = useState("");
  const [signupCountry, setSignupCountry] = useState("");
  const [insurance, setInsurance] = useState(false);
  const [crewID, setCrewID] = useState("");
  const [crewPassword, setCrewPassword] = useState("");
  const [showPassnegerList, setShowPassengerList] = useState(false);
  const [passengerList, setPassengerList] = useState([]);
  const [isRegisteredUser, setIsRegisteredUser] = useState(false);
  const [loginAttemptError, setLoginAttemptError] = useState("");
  const uniqueDestOptions = [...new Set(destOptions)];
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const [showCrewLogin, setShowCrewLogin] = useState(false);
  const [cancelNotification, setCancelNotification] = useState({
    message: "",
    isSuccess: false,
    isVisible: false,
  });

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
    } else if (lowerCaseOption === "third option") {
      setSelectedOption("signup");
    } else if (lowerCaseOption === "crew view") {
      setShowModal(false);
      setSelectedOption("crew login");
      setShowCrewLogin(true);
    }

    getAllDestinations();
  };

  const handleSignup = (e) => {
    e.preventDefault();

    if (!emailRegex.test(signupEmail)) {
      setEmailError("Please enter a valid email address.");
      return;
    }

    const signupData = {
      name: signupUsername,
      emailAddr: signupEmail,
      phoneNum: signupPhone,
      customerPassword: signupPassword,
    };
    console.log("Signup Data:", signupData);

    fetch(
      `http://localhost:8080/FlightApp/Customer/Create/${signupAddress}/${signupPostalCode}/${signupCity}/${signupCountry}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(signupData),
      }
    );

    setSignupUsername("");
    setSignupEmail("");
    setSignupPhone("");
    setSignupPassword("");
    setSignupAddress("");
    setSignupPostalCode("");
    setSignupCity("");
    setSignupCountry("");

    // ***HOW DO WE REDIRECT TO FRONT PAGE *** //
    setSelectedOption(null); // Assuming this clears the signup form and displays initial options
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

  const checkCrewLogin = async () => {
    const recieve = await fetch(
      `http://localhost:8080/FlightApp/Crew/Validation/${crewID}/${crewPassword}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    const res = await recieve.json();
    if (res === true) {
      setShowPassengerList(true);
      setShowCrewLogin(false);
      const recieve = await fetch(
        `http://localhost:8080/FlightApp/Flight/GetPassengerList/${crewID}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      const customers = await recieve.json();
      setPassengerList(customers);
      console.log("Returned Customers:", customers);
      console.log("Set Customers:", passengerList);
    } else if (res === false) {
      setSelectedOption(null);
      setCrewID("");
      setCrewPassword("");
      window.alert(
        "You have attempted to access confidential information without proper authentication credentials. Page exited"
      );
    }
  };

  const handleModalClose = () => {
    setShowModal(false);
    //setUsername("");
    setPassword("");
    //setRole(""); // Reset the role when the modal is closed
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    console.log("Username:", username);
    console.log("Password:", password);
    const recieve = await fetch(
      `http://localhost:8080/FlightApp/Customer/Validate/${username}/${password}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    const validateAcc = await recieve.json();
    if (validateAcc === true) {
      setIsRegisteredUser(true);
      setLoggedInUser(username);
      handleModalClose();
    } else if (validateAcc === false) {
      setUsername("");
      setPassword("");
      setLoginAttemptError(
        "The given userrname or password is incorrect. Please try again."
      );
      return;
    }
  };

  const handleTicketPurchase = () => {
    console.log("Destination:", selectedDest);
    getAllFlightsForLocation(); // Fetch and set available flights
    setShowTicketPurchaseForm(false);
    setShowSearchFlight(true); // Show the flight search UI
  };

  const handleFlightSelection = (flight) => {
    console.log("Selected flight:", flight);
    // console.log("CHECK:", sendFlightID);
    // Implement what happens after a flight is selected, e.g., storing flight data
    // and transitioning to the seat selection phase
    setShowSearchFlight(false);
    setShowSeatSelection(true);
  };

  const handleCancelTicket = async () => {
    const response = await fetch(
      `http://localhost:8080/FlightApp/Ticket/Cancel/${ticketId}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    const apiResponse = await response.json();

    if (apiResponse === 1) {
      setCancelNotification({
        message: `Ticket cancellation successful! ${
          insurance
            ? "You purchased ticket cancellation insurance, thus you will get a full refund!"
            : "You did not purchase ticket cancellation insurance, thus you will get a half refund."
        }`,
        isSuccess: true,
        isVisible: true,
      });

      setTimeout(() => {
        setCancelNotification({
          message: "",
          isSuccess: false,
          isVisible: false,
        });
        setSelectedOption(null); // Navigate back to the home page
      }, 4000);
    } else {
      setCancelNotification({
        message: "Ticket cancellation unsuccessful. Please try again.",
        isSuccess: false,
        isVisible: true,
      });
    }
  };

  const handleLogout = () => {
    // Clear necessary states and reset them to default values
    setLoggedInUser(null);
    setSelectedOption(null);
    setShowModal(false);
    setUsername("");
    setPassword("");
    setShowTicketPurchaseForm(false);
    setSelectedDest("");
    setSendFlightID(0);
    setSendSeatID(0);
    setInsurance(false);
    setShowSeatSelection(false);
    setShowPayment(false);
    setShowInsurance(false);
    setShowSearchFlight(false);
    setShowCrewLogin(false); // Ensure crew login form is hidden
    setIsRegisteredUser(false);
    setCancelNotification({
      message: "",
      isSuccess: false,
      isVisible: false,
    });
  };

  const handleSeatSelect = (section, row, seat, continueToInsurance) => {
    if (continueToInsurance) {
      setShowSeatSelection(false);
      setShowInsurance(true);
    }

    const seatID = (sendFlightID - 1) * 15 + row * 5 + (seat + 1);
    setSendSeatID(seatID);
  };

  const handlePaymentSubmit = (paymentDetails) => {
    console.log("Payment Details:", paymentDetails);
    // Handle the payment submission logic here
    setShowPayment(false);
    setSelectedOption(null);
  };

  const handleInsuranceContinue = () => {
    setShowInsurance(false);
    setShowPayment(true);
    console.log("AFTER INS CHOSEN:", insurance);
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
            <button
              className="btn btn-primary"
              type="button"
              onClick={() => handleButtonClick("cancel ticket")}
            >
              Cancel Ticket
            </button>
            <button
              className="btn btn-primary"
              type="button"
              onClick={() => handleButtonClick("crew view")}
            >
              Crew Login
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

        {selectedOption === "signup" && (
          <div className="signup-form">
            <h2>Sign Up</h2>
            <form onSubmit={handleSignup}>
              <label htmlFor="signup-username">First and Last Name</label>
              <input
                type="text"
                id="signup-username"
                value={signupUsername}
                onChange={(e) => setSignupUsername(e.target.value)}
                required
              />
              <label htmlFor="signup-email">Email</label>
              <input
                type="email"
                id="signup-email"
                value={signupEmail}
                onChange={(e) => {
                  setSignupEmail(e.target.value);
                  setEmailError(""); // Clear email error on input change
                }}
                required
              />
              {emailError && <p style={{ color: "red" }}>{emailError}</p>}

              <label htmlFor="signup-phone">Phone Number</label>
              <input
                type="tel"
                id="signup-phone"
                value={signupPhone}
                onChange={(e) => setSignupPhone(e.target.value)}
                required
              />
              <label htmlFor="signup-password">Password</label>
              <input
                type="password"
                id="signup-password"
                value={signupPassword}
                onChange={(e) => setSignupPassword(e.target.value)}
                required
              />
              <label htmlFor="signup-address">Address</label>
              <input
                type="text"
                id="signup-address"
                value={signupAddress}
                onChange={(e) => setSignupAddress(e.target.value)}
                required
              />

              <label htmlFor="signup-postal-code">Postal Code</label>
              <input
                type="text"
                id="signup-postal-code"
                value={signupPostalCode}
                onChange={(e) => setSignupPostalCode(e.target.value)}
                required
              />

              <label htmlFor="signup-city">City</label>
              <input
                type="text"
                id="signup-city"
                value={signupCity}
                onChange={(e) => setSignupCity(e.target.value)}
                required
              />

              <label htmlFor="signup-country">Country</label>
              <input
                type="text"
                id="signup-country"
                value={signupCountry}
                onChange={(e) => setSignupCountry(e.target.value)}
                required
              />
              <button type="submit">Sign Up</button>
            </form>
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
            {/* <button
              className="btn btn-secondary"
              type="button"
              onClick={() => handleButtonClick("cancel ticket")}
            >
              Cancel Ticket
            </button> */}
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
                <form onSubmit={handleLogin}>
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
                    onChange={(e) => {
                      setPassword(e.target.value);
                      setLoginAttemptError("");
                    }}
                  />

                  {loginAttemptError && (
                    <p style={{ color: "red" }}>{loginAttemptError}</p>
                  )}
                  {/* <div className="input-group mb-3">
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
                  </div> */}

                  <button type="submit">Log In</button>
                </form>
              </div>
            </div>
          )}

        {showCrewLogin && selectedOption === "crew login" && (
          <div className="crew-login-input">
            <label htmlFor="crewId">Enter your Crew ID</label>
            <input
              type="text"
              id="crewId"
              value={crewID}
              onChange={(e) => setCrewID(e.target.value)}
            />

            <label htmlFor="crewPass">Enter your Password</label>
            <input
              type="text"
              id="crewPass"
              value={crewPassword}
              onChange={(e) => setCrewPassword(e.target.value)}
            />
            <button onClick={() => checkCrewLogin()}>Login</button>
          </div>
        )}

        {showPassnegerList && (
          <div className="crew-login-input">
            {passengerList.length > 0 ? (
              <div>
                {passengerList.map((passenger, index) => (
                  <div key={index}>
                    <p>Name: {passenger.name}</p>
                    <p>Email: {passenger.emailAddr}</p>
                    <p>Phone Number: {passenger.phoneNum}</p>
                    <p>ID: {passenger.customerID}</p>
                  </div>
                ))}
              </div>
            ) : (
              <p>
                No passengers have currently booked a ticket for your flight.
              </p>
            )}
          </div>
        )}

        {cancelNotification.isVisible && (
          <div
            className={`notification ${
              cancelNotification.isSuccess ? "success" : "error"
            }`}
          >
            {cancelNotification.message}
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
            <button onClick={handleCancelTicket}>Cancel Ticket</button>
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
                {uniqueDestOptions.map((destination, index) => (
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
                    <h3>Flight: {flight.flight_id}</h3>
                    <p>
                      From: {flight.departureCity} {flight.departureCountry}{" "}
                      {flight.departureAirport}
                    </p>
                    <p>
                      To: {flight.destinationCity} {flight.destinationCountry}{" "}
                      {flight.destinationAirport}
                    </p>
                    <p>
                      Departure: {flight.departureDate} at{" "}
                      {flight.departureTime} - Arrival: {flight.arrivalDate} at{" "}
                      {flight.arrivalTime}
                    </p>
                    <button
                      onClick={() => {
                        setSendFlightID(flight.flight_id);
                        handleFlightSelection(flight);
                      }}
                    >
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
            <SeatSelection
              onSeatSelect={handleSeatSelect}
              flightID={sendFlightID}
            />
          )}
        {showInsurance && (
          <Insurance
            onInsuranceSubmit={handleInsuranceContinue}
            setInsurance={setInsurance}
          />
        )}
        {isRegisteredUser
          ? showPayment && (
              <Payment
                onPaymentSubmit={handlePaymentSubmit}
                hasInsurance={insurance}
                seatID={sendSeatID}
                flightID={sendFlightID}
                email={username}
              />
            )
          : showPayment && (
              <GuestPayment
                onPaymentSubmit={handlePaymentSubmit}
                hasInsurance={insurance}
                seatID={sendSeatID}
                flightID={sendFlightID}
              />
            )}
      </div>
    </div>
  );
}

export default App;
