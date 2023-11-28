import React, { useState } from "react";
import "./SeatSelection.css"; // Make sure to style your seats and rows appropriately in CSS

function SeatSelection({ onSeatSelect }) {
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
      onSeatSelect(null);
    } else {
      setSelectedSeat(seatInfo); // Select new seat
      onSeatSelect(seatInfo);
    }
  };

  return (
    <div className="seat-selection-container">
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
    </div>
  );
}

export default SeatSelection;
