import React, { useState } from "react";
import "./SeatSelection.css"; // Ensure your CSS file is correctly linked

function SeatSelection({ onSeatSelect, onContinue }) {
  const rows = 3; // Total number of rows
  const seatsPerRow = 5; // Seats per row
  const [selectedSeat, setSelectedSeat] = useState(null);

  const handleSeatClick = (row, seat) => {
    const seatInfo = { row, seat };

    // Toggle seat selection
    if (
      selectedSeat &&
      selectedSeat.row === row &&
      selectedSeat.seat === seat
    ) {
      setSelectedSeat(null); // Deselect if the same seat is clicked
    } else {
      setSelectedSeat(seatInfo); // Select new seat
    }
  };

  const handleContinue = () => {
    if (selectedSeat) {
      onSeatSelect(selectedSeat.row, selectedSeat.seat, true);
    } else {
      alert("Please select a seat first.");
    }

    // Adding a flag to indicate continuation
  };

  return (
    <div className="seat-selection-container">
      {/* Seat Map */}
      {Array.from({ length: rows }, (_, rowIndex) => (
        <div key={rowIndex} className="seat-row">
          {Array.from({ length: seatsPerRow }, (_, seatIndex) => (
            <button
              key={seatIndex}
              className={`seat ${
                selectedSeat?.row === rowIndex &&
                selectedSeat?.seat === seatIndex
                  ? "selected"
                  : ""
              }`}
              onClick={() => handleSeatClick(rowIndex, seatIndex)}
            >
              {rowIndex + 1}-{seatIndex + 1}
            </button>
          ))}
        </div>
      ))}

      {/* Continue Button */}
      <button className="continue-button" onClick={handleContinue}>
        Continue
      </button>
    </div>
  );
}

export default SeatSelection;
