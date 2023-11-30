package com.example.FlightAppDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;


@SpringBootApplication  //combination of three annotations
public class FlightAppDemoApplication implements CommandLineRunner{

	@Autowired
	AircraftRepository aircraftInterface;

	@Autowired
	CustomerRepository custInterface;

	@Autowired
	FlightRepository flightInterface;

	@Autowired
	CrewRepository crewInterface;

	@Autowired
	MembershipRepository memberInterface;

	@Autowired
	SeatRepository seatInterface;

	@Autowired
	TicketRepository ticketInterface;

	@Autowired
	PaymentRepository paymentInterface;

	public static void main(String[] args) {
		SpringApplication.run(FlightAppDemoApplication.class, args);
	}

	@Override
	public void run(String... args){
		Customer customer1 = new Customer("Alice", "alice@example.com", "+1234567890", "abc");
		Customer customer2 = new Customer("Bob", "bob@example.com", "+1987654321", "xyz");
		Customer customer3 = new Customer("Charlie", "charlie@example.com", "+1654321890", "def");
		Customer customer4 = new Customer("Diana", "diana@example.com", "+1789456231", "ghi");
		Customer customer5 = new Customer("Eve", "eve@example.com", "+1567894321", "lmn");

		Aircraft aircraft1 = new Aircraft("Boeing 757", 30, 20, true);
		Aircraft aircraft2 = new Aircraft("Airbus A320", 150, 25, false);
		Aircraft aircraft3 = new Aircraft("Cessna 172", 4, 5, true);
		Aircraft aircraft4 = new Aircraft("Embraer E190", 100, 22, false);
		Aircraft aircraft5 = new Aircraft("Boeing 787", 250, 30, true);

		//Creating Flight objects using the parameterized constructor
		Flight flight1 = new Flight("2023-12-01", "08:00", "2023-12-01", "10:30", "New York", "USA", "JFK",
				"London", "UK", "Heathrow");

		Flight flight2 = new Flight("2023-12-02", "12:00", "2023-12-02", "18:30", "Paris", "France", "CDG",
				"Tokyo", "Japan", "Narita");

		Flight flight3 = new Flight("2023-12-03", "10:30", "2023-12-03", "13:45", "Sydney", "Australia", "SYD",
				"Dubai", "UAE", "DXB");

		Flight flight4 = new Flight("2023-12-04", "09:15", "2023-12-04", "15:00", "Los Angeles", "USA", "LAX",
				"Beijing", "China", "PEK");

		Flight flight5 = new Flight("2023-12-05", "16:45", "2023-12-05", "19:20", "Moscow", "Russia", "SVO",
				"Singapore", "Singapore", "Changi");

		Flight flight6 = new Flight("2023-12-06", "07:30", "2023-12-06", "13:45", "New York", "USA", "JFK", "Dubai", "UAE", "DXB");


		Crew crew1 = new Crew(1, "Alice", "Pilot");
		Crew crew2 = new Crew(2, "Bob", "Co-Pilot");
		Crew crew3 = new Crew(3, "Charlie", "Flight Attendant");
		Crew crew4 = new Crew(4, "Diana", "Engineer");
		Crew crew5 = new Crew(5, "Eve", "Navigator");

		Membership membership1 = new Membership("2023-01-01", "123 Main St", "12345", "City1", "Country1", "2023-12-31");
		Membership membership2 = new Membership("2023-02-15", "456 Elm St", "23456", "City2", "Country2", "2023-12-31");
		Membership membership3 = new Membership("2023-03-20", "789 Oak St", "34567", "City3", "Country3", "2023-12-31");
		Membership membership4 = new Membership("2023-04-10", "101 Pine St", "45678", "City4", "Country4", "2023-12-31");
		Membership membership5 = new Membership("2023-05-05", "222 Maple St", "56789", "City5", "Country5", "2023-12-31");

		Seat seat1 = new Seat("1A", 1, 1, true);  // Economy seat in row 1, seat number A1
		Seat seat2 = new Seat("1B", 1, 1, false);  // Business seat in row 2, seat number B3
		Seat seat3 = new Seat("1C", 1, 1, false);  // First class seat in row 3, seat number C5
		Seat seat4 = new Seat("2A", 2, 2, false);  // Economy seat in row 4, seat number D2
		Seat seat5 = new Seat("2B", 2, 2, false);  // Business seat in row 5, seat number E4
		Seat seat6 = new Seat("2C", 2, 2, false);
		Seat seat7 = new Seat("3A", 3, 3, false);
		Seat seat8 = new Seat("3B", 3, 3, false);
		Seat seat9 = new Seat("3C", 3, 3, false);
		Seat seat10 = new Seat("4A", 4, 3, false);
		Seat seat11 = new Seat("4B", 4, 3, false);
		Seat seat12 = new Seat("4C", 4, 3, false);
		Seat seat13 = new Seat("5A", 5, 3, false);
		Seat seat14 = new Seat("5B", 5, 3, false);
		Seat seat15 = new Seat("5C", 5, 3, false);

		// Seats 16 to 30
		Seat seat16 = new Seat("1A", 1, 3, false);
		Seat seat17 = new Seat("1B", 1, 3, false);
		Seat seat18 = new Seat("1C", 1, 3, false);
		Seat seat19 = new Seat("2A", 2, 3, false);
		Seat seat20 = new Seat("2B", 2, 3, false);
		Seat seat21 = new Seat("2C", 2, 3, false);
		Seat seat22 = new Seat("3A", 3, 3, false);
		Seat seat23 = new Seat("3B", 3, 3, false);
		Seat seat24 = new Seat("3C", 3, 3, false);
		Seat seat25 = new Seat("4A", 4, 3, false);
		Seat seat26 = new Seat("4B", 4, 3, false);
		Seat seat27 = new Seat("4C", 4, 3, false);
		Seat seat28 = new Seat("5A", 5, 3, false);
		Seat seat29 = new Seat("5B", 5, 3, false);
		Seat seat30 = new Seat("5C", 5, 3, false);

		// Seats 31 to 45
		Seat seat31 = new Seat("1A", 1, 3, false);
		Seat seat32 = new Seat("1B", 1, 3, false);
		Seat seat33 = new Seat("1C", 1, 3, false);
		Seat seat34 = new Seat("2A", 2, 3, false);
		Seat seat35 = new Seat("2B", 2, 3, false);
		Seat seat36 = new Seat("2C", 2, 3, false);
		Seat seat37 = new Seat("3A", 3, 3, false);
		Seat seat38 = new Seat("3B", 3, 3, false);
		Seat seat39 = new Seat("3C", 3, 3, false);
		Seat seat40 = new Seat("4A", 4, 3, false);
		Seat seat41 = new Seat("4B", 4, 3, false);
		Seat seat42 = new Seat("4C", 4, 3, false);
		Seat seat43 = new Seat("5A", 5, 3, false);
		Seat seat44 = new Seat("5B", 5, 3, false);
		Seat seat45 = new Seat("5C", 5, 3, false);



		Ticket ticket1 = new Ticket(150.0f, "false");
		Ticket ticket2 = new Ticket(200.0f, "false");
		Ticket ticket3 = new Ticket(180.0f, "false");
		Ticket ticket4 = new Ticket(220.0f, "false");
		Ticket ticket5 = new Ticket(190.0f, "false");
		
		Payment payment1 = new Payment("1234567890123456", "12/25", 123, 200);
		Payment payment2 = new Payment("9876543210987654", "10/24", 456, 150);
		Payment payment3 = new Payment("1111222233334444", "08/23", 789, 180);
		Payment payment4 = new Payment("5555666677778888", "06/22", 321, 220);
		Payment payment5 = new Payment("9999888877776666", "04/21", 654, 190);

		//SETTING AND SAVING
		membership1.setCustomer(customer1);
		membership2.setCustomer(customer2);
		membership3.setCustomer(customer3);
		membership4.setCustomer(customer4);
		membership5.setCustomer(customer5);


		aircraftInterface.save(aircraft1);
		aircraftInterface.save(aircraft2);
		aircraftInterface.save(aircraft3);
		aircraftInterface.save(aircraft4);
		aircraftInterface.save(aircraft5);

		flight1.setAircraft(aircraft1);
		flight2.setAircraft(aircraft2);
		flight3.setAircraft(aircraft3);
		flight4.setAircraft(aircraft4);
		flight5.setAircraft(aircraft5);  //lol

		crew1.setFlight(flight1);
		crew2.setFlight(flight2);
		crew3.setFlight(flight3);
		crew4.setFlight(flight4);
		crew5.setFlight(flight5);

		flightInterface.save(flight1);
		flightInterface.save(flight2);
		flightInterface.save(flight3);
		flightInterface.save(flight4);
		flightInterface.save(flight5);
		flightInterface.save(flight6);

		// Set all seats to flight1
		seat1.setFlight(flight1);
		seat2.setFlight(flight1);
		seat3.setFlight(flight1);
		seat4.setFlight(flight1);
		seat5.setFlight(flight1);
		seat6.setFlight(flight1);
		seat7.setFlight(flight1);
		seat8.setFlight(flight1);
		seat9.setFlight(flight1);
		seat10.setFlight(flight1);
		seat11.setFlight(flight1);
		seat12.setFlight(flight1);
		seat13.setFlight(flight1);
		seat14.setFlight(flight1);
		seat15.setFlight(flight1);

		// Set all seats to flight2
		seat16.setFlight(flight2);
		seat17.setFlight(flight2);
		seat18.setFlight(flight2);
		seat19.setFlight(flight2);
		seat20.setFlight(flight2);
		seat21.setFlight(flight2);
		seat22.setFlight(flight2);
		seat23.setFlight(flight2);
		seat24.setFlight(flight2);
		seat25.setFlight(flight2);
		seat26.setFlight(flight2);
		seat27.setFlight(flight2);
		seat28.setFlight(flight2);
		seat29.setFlight(flight2);
		seat30.setFlight(flight2);


		// Set all seats to flight3
		seat31.setFlight(flight3);
		seat32.setFlight(flight3);
		seat33.setFlight(flight3);
		seat34.setFlight(flight3);
		seat35.setFlight(flight3);
		seat36.setFlight(flight3);
		seat37.setFlight(flight3);
		seat38.setFlight(flight3);
		seat39.setFlight(flight3);
		seat40.setFlight(flight3);
		seat41.setFlight(flight3);
		seat42.setFlight(flight3);
		seat43.setFlight(flight3);
		seat44.setFlight(flight3);
		seat45.setFlight(flight3);



		seatInterface.save(seat1);
		seatInterface.save(seat2);
		seatInterface.save(seat3);
		seatInterface.save(seat4);
		seatInterface.save(seat5);
		seatInterface.save(seat6);
		seatInterface.save(seat7);
		seatInterface.save(seat8);
		seatInterface.save(seat9);
		seatInterface.save(seat10);
		seatInterface.save(seat11);
		seatInterface.save(seat12);
		seatInterface.save(seat13);
		seatInterface.save(seat14);
		seatInterface.save(seat15);
		seatInterface.save(seat16);
		seatInterface.save(seat17);
		seatInterface.save(seat18);
		seatInterface.save(seat19);
		seatInterface.save(seat20);
		seatInterface.save(seat21);
		seatInterface.save(seat22);
		seatInterface.save(seat23);
		seatInterface.save(seat24);
		seatInterface.save(seat25);
		seatInterface.save(seat26);
		seatInterface.save(seat27);
		seatInterface.save(seat28);
		seatInterface.save(seat29);
		seatInterface.save(seat30);
		seatInterface.save(seat31);
		seatInterface.save(seat32);
		seatInterface.save(seat33);
		seatInterface.save(seat34);
		seatInterface.save(seat35);
		seatInterface.save(seat36);
		seatInterface.save(seat37);
		seatInterface.save(seat38);
		seatInterface.save(seat39);
		seatInterface.save(seat40);
		seatInterface.save(seat41);
		seatInterface.save(seat42);
		seatInterface.save(seat43);
		seatInterface.save(seat44);
		seatInterface.save(seat45);


		crewInterface.save(crew1);
		crewInterface.save(crew2);
		crewInterface.save(crew3);
		crewInterface.save(crew4);
		crewInterface.save(crew5);

		custInterface.save(customer1);
		custInterface.save(customer2);
		custInterface.save(customer3);
		custInterface.save(customer4);
		custInterface.save(customer5);

		memberInterface.save(membership1);
		memberInterface.save(membership2);
		memberInterface.save(membership3);
		memberInterface.save(membership4);
		memberInterface.save(membership5);

		ticket1.setPassenger(customer1);
		ticket1.setFlight(flight1);
		ticket1.setSeat(seat1);

		ticket2.setPassenger(customer2);
		ticket1.setFlight(flight2);
		ticket2.setSeat(seat2);

		ticketInterface.save(ticket1);
		ticketInterface.save(ticket2);

		payment1.setTicket(ticket1);
		paymentInterface.save(payment1);

	}
}
