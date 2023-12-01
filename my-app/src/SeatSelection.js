import React, { useState, useEffect } from "react";
import "./SeatSelection.css"; // Import the CSS file for styling

function SeatSelection({ onSeatSelect, flightID }) {
  const rows = 3;
  const seatsPerRow = 5;
  const sections = 1; // Number of sections
  const [selectedSeat, setSelectedSeat] = useState(null);
  const [takenSeats, setTakenSeats] = useState([]);

  useEffect(() => {
    // Fetch taken seats from the backend
    const fetchTakenSeats = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/FlightApp/Seat/GetAllTakenOrNot/ByFlightID/${flightID}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
        const data = await response.json();
        setTakenSeats(data); 
      } catch (error) {
        console.error("Error fetching taken seats:", error);
      }
    };

    fetchTakenSeats();
  }, [flightID]);

  const isSeatTaken = (index) => {
    return takenSeats[index];
  };

  const handleSeatClick = (section, row, seat) => {
    const seatIndex = row * seatsPerRow + seat;

    if (isSeatTaken(seatIndex)) {
      // If the seat is already taken, do not proceed
      return;
    }

    if (
      selectedSeat &&
      selectedSeat.section === section &&
      selectedSeat.row === row &&
      selectedSeat.seat === seat
    ) {
      setSelectedSeat(null);
      onSeatSelect(null, null, null);
    } else {
      setSelectedSeat({ section, row, seat });
      onSeatSelect(section, row, seat);
    }
  };

  const handleContinue = () => {
    onSeatSelect(
      selectedSeat.section,
      selectedSeat.row,
      selectedSeat.seat,
      true
    ); // Adding a flag to indicate continuation
    console.log(selectedSeat.row);
    console.log(selectedSeat.seat);
    console.log(flightID);
    const seatIDToSend = (((flightID - 1)*15) + (selectedSeat.row*5) + (selectedSeat.seat + 1));
    console.log(seatIDToSend);

    fetch(`http://localhost:8080/FlightApp/Seat/SeatTaken/${seatIDToSend}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
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
          const seatIndex = i * seatsPerRow + j;
          const isDisabled = isSeatTaken(seatIndex);

          row.push(
            <button
              key={`seat-${s}-${i}-${j}`}
              onClick={() => handleSeatClick(s, i, j)}
              className={`seat ${isSelected ? "selected" : ""}`}
              disabled={isDisabled}
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
    <>
    <div>
      <p>Pink: Comfort</p>
      <p>Orange: Business</p>
      <p>Grey: Economy</p>
    </div>
    <div className="seat-selection">
      {renderSeats()}
      {selectedSeat && (
        <button className="continue-button" onClick={handleContinue}>
          Continue
        </button>
      )}
    </div>
    </>
  );
}

export default SeatSelection;
