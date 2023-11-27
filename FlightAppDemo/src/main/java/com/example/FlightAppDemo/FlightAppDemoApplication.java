package com.example.FlightAppDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



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
		Customer customer1 = new Customer("Alice", "alice@example.com", "+1234567890");
		Customer customer2 = new Customer("Bob", "bob@example.com", "+1987654321");
		Customer customer3 = new Customer("Charlie", "charlie@example.com", "+1654321890");
		Customer customer4 = new Customer("Diana", "diana@example.com", "+1789456231");
		Customer customer5 = new Customer("Eve", "eve@example.com", "+1567894321");

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

		Seat seat1 = new Seat("A1", 1, 1);  // Economy seat in row 1, seat number A1
		Seat seat2 = new Seat("B3", 2, 2);  // Business seat in row 2, seat number B3
		Seat seat3 = new Seat("C5", 3, 3);  // First class seat in row 3, seat number C5
		Seat seat4 = new Seat("D2", 4, 1);  // Economy seat in row 4, seat number D2
		Seat seat5 = new Seat("E4", 5, 2);  // Business seat in row 5, seat number E4

		Ticket ticket1 = new Ticket(150.0f);
		Ticket ticket2 = new Ticket(200.0f);
		Ticket ticket3 = new Ticket(180.0f);
		Ticket ticket4 = new Ticket(220.0f);
		Ticket ticket5 = new Ticket(190.0f);
		
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
		flight5.setAircraft(aircraft5);

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

		seat1.setFlight(flight1);
		seat2.setFlight(flight2);
		seat3.setFlight(flight3);
		seat4.setFlight(flight4);
		seat5.setFlight(flight5);

		seatInterface.save(seat1);
		seatInterface.save(seat2);
		seatInterface.save(seat3);
		seatInterface.save(seat4);
		seatInterface.save(seat5);

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
