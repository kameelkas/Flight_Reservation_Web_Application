import React, { useState } from "react";
import "./SeatSelection.css"; // Import the CSS file for styling

function SeatSelection({ onSeatSelect }) {
  const rows = 3;
  const seatsPerRow = 5;
  const sections = 1; // Number of sections
  const [selectedSeat, setSelectedSeat] = useState(null);

  const handleSeatClick = (section, row, seat) => {
    // If the clicked seat is already selected, deselect it
    if (
      selectedSeat &&
      selectedSeat.section === section &&
      selectedSeat.row === row &&
      selectedSeat.seat === seat
    ) {
      setSelectedSeat(null);
      onSeatSelect(null, null, null); // Notify the parent component of deselection
    } else {
      setSelectedSeat({ section, row, seat });
      onSeatSelect(section, row, seat); // Notify the parent component of the selected seat
    }
  };

  const handleContinue = () => {
    onSeatSelect(
      selectedSeat.section,
      selectedSeat.row,
      selectedSeat.seat,
      true
    ); // Adding a flag to indicate continuation
  };

  const renderSeats = () => {
    const seatMap = [];
    for (let s = 0; s < sections; s++) {
      const sectionRows = [];
      for (let i = 0; i < rows; i++) {
        const row = [];
        for (let j = 0; j < seatsPerRow; j++) {
          const seatNumber = j + 1;
          const isSelected =
            selectedSeat &&
            selectedSeat.section === s &&
            selectedSeat.row === i &&
            selectedSeat.seat === j;
          row.push(
            <button
              key={`seat-${s}-${i}-${j}`}
              onClick={() => handleSeatClick(s, i, j)}
              className={`seat ${isSelected ? "selected" : ""}`}
            >
              {`S${s + 1}-R${i + 1}-Seat${seatNumber}`}
            </button>
          );
        }
        sectionRows.push(
          <div key={`row-${s}-${i}`} className="seat-row">
            {row}
          </div>
        );
      }
      seatMap.push(
        <div key={`section-${s}`} className="section">
          <h2>Seat Map</h2>
          <div className="seat-map">{sectionRows}</div>
        </div>
      );
    }
    return seatMap;
  };

  return (
    <div className="seat-selection">
      {renderSeats()}
      {selectedSeat && (
        <button className="continue-button" onClick={handleContinue}>
          Continue
        </button>
      )}
    </div>
  );
}

export default SeatSelection;
