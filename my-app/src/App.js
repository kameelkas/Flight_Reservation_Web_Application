import React, { useState } from "react";
import "./App.css";

function App() {
  const [selectedOption, setSelectedOption] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loggedInUser, setLoggedInUser] = useState(null); // New state for logged-in user

  const handleButtonClick = (option) => {
    setSelectedOption(option);
    if (option === "Member") {
      setShowModal(true);
    }
  };

  const handleModalClose = () => {
    setShowModal(false);
    setUsername("");
    setPassword("");
  };

  const handleLogin = () => {
    // Handle login logic with username and password
    console.log("Username:", username);
    console.log("Password:", password);
    setLoggedInUser(username); // Set logged-in user
    handleModalClose();
  };
  return (
    <div className="container ">
      <div className="header">
        <h2 className="title">Flight Booker</h2>
        {loggedInUser && (
          <div className="user-info">
            <span>Logged in as {loggedInUser}</span>
          </div>
        )}
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
              System Admin
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
            <button
              className="btn btn-primary"
              type="button"
              onClick={() => handleButtonClick("Member")}
            >
              Member
            </button>
            <button className="btn btn-primary" type="button">
              Guest
            </button>
          </div>
        )}
        {showModal && selectedOption === "Member" && (
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
                <button type="button" onClick={handleLogin}>
                  Login
                </button>
              </form>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
