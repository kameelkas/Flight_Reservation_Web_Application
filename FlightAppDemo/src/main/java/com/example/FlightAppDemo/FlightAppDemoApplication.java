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
		Customer customer1 = new Customer("Rohil", "1234@gmail.com", "+1234567890", "abc");
		Customer customer2 = new Customer("Kameel", "456@gmail.com", "+1987654321", "xyz");
		Customer customer3 = new Customer("Raisa", "rafi.raisar@gmail.com", "+1654321890", "def");
		Customer customer4 = new Customer("Mehrnaz", "merymer920@gmail.com", "+1789456231", "ghi");
		Customer customer5 = new Customer("RohilAgain", "compsciencerohil@gmail.com", "+1567894321", "lmn");

		Aircraft aircraft1 = new Aircraft("Boeing 757", 30, 20, true);
		Aircraft aircraft2 = new Aircraft("Airbus A320", 150, 25, false);
		Aircraft aircraft3 = new Aircraft("Cessna 172", 4, 5, true);
		Aircraft aircraft4 = new Aircraft("Embraer E190", 100, 22, false);
		Aircraft aircraft5 = new Aircraft("Boeing 787", 250, 30, true);

		//Creating Flight objects using the parameterized constructor
		Flight flight1 = new Flight("2023-12-01", "08:00", "2023-12-01", "10:30", "New York", "USA", "JFK",
				"London", "UK", "Heathrow", 1000);

		Flight flight2 = new Flight("2023-12-02", "12:00", "2023-12-02", "18:30", "Paris", "France", "CDG",
				"Tokyo", "Japan", "Narita", 1100);

		Flight flight3 = new Flight("2023-12-03", "10:30", "2023-12-03", "13:45", "Sydney", "Australia", "SYD",
				"Dubai", "UAE", "DXB", 1200);

		Flight flight4 = new Flight("2023-12-04", "09:15", "2023-12-04", "15:00", "Los Angeles", "USA", "LAX",
				"Beijing", "China", "PEK", 1300);

		Flight flight5 = new Flight("2023-12-05", "16:45", "2023-12-05", "19:20", "Moscow", "Russia", "SVO",
				"Singapore", "Singapore", "Changi", 1450);

		Flight flight6 = new Flight("2023-12-06", "07:30", "2023-12-06", "13:45", "New York", "USA", "JFK", "Dubai", "UAE", "DXB", 1500);


		Crew crew1 = new Crew(1, "Alice", "Pilot", "123");
		Crew crew2 = new Crew(2, "Bob", "Co-Pilot", "456");
		Crew crew3 = new Crew(3, "Charlie", "Flight Attendant", "789");
		Crew crew4 = new Crew(4, "Diana", "Engineer", "910");
		Crew crew5 = new Crew(5, "Eve", "Navigator", "911");

		Membership membership1 = new Membership("123 Main St", "12345", "City1", "Country1");
		Membership membership2 = new Membership("456 Elm St", "23456", "City2", "Country2");
		Membership membership3 = new Membership("789 Oak St", "34567", "City3", "Country3");
		Membership membership4 = new Membership("101 Pine St", "45678", "City4", "Country4");
		Membership membership5 = new Membership("222 Maple St", "56789", "City5", "Country5");

		Seat seat1 = new Seat("1A", 1, 1, true, 300);
		Seat seat2 = new Seat("1B", 1, 1, false, 300);
		Seat seat3 = new Seat("1C", 1, 1, false, 300);
		Seat seat4 = new Seat("1D", 1, 1, false, 300);
		Seat seat5 = new Seat("1E", 1, 1, false, 300);

		Seat seat6 = new Seat("2A", 2, 2, false, 200);
		Seat seat7 = new Seat("2B", 2, 2, false, 200);
		Seat seat8 = new Seat("2C", 2, 2, false, 200);
		Seat seat9 = new Seat("2D", 2, 2, false, 200);
		Seat seat10 = new Seat("2E", 2, 2, false, 200);

		Seat seat11 = new Seat("3A", 3, 3, false, 50);
		Seat seat12 = new Seat("3B", 3, 3, false, 50);
		Seat seat13 = new Seat("3C", 3, 3, false, 50);
		Seat seat14 = new Seat("3D", 3, 3, false, 50);
		Seat seat15 = new Seat("3E", 3, 3, false, 50);

		// Seats 16 to 30
		Seat seat16 = new Seat("1A", 1, 1, false, 300);
		Seat seat17 = new Seat("1B", 1, 1, false, 300);
		Seat seat18 = new Seat("1C", 1, 1, false, 300);
		Seat seat19 = new Seat("1D", 1, 1, false, 300);
		Seat seat20 = new Seat("1E", 1, 1, false, 300);

		Seat seat21 = new Seat("2A", 2, 2, false, 200);
		Seat seat22 = new Seat("2B", 2, 2, false, 200);
		Seat seat23 = new Seat("2C", 2, 2, false, 200);
		Seat seat24 = new Seat("2D", 2, 2, false, 200);
		Seat seat25 = new Seat("2E", 2, 2, false, 200);

		Seat seat26 = new Seat("3A", 3, 3, false, 50);
		Seat seat27 = new Seat("3B", 3, 3, false, 50);
		Seat seat28 = new Seat("3C", 3, 3, false, 50);
		Seat seat29 = new Seat("3D", 3, 3, false, 50);
		Seat seat30 = new Seat("3E", 3, 3, false, 50);

		// Seats 31 to 45
		Seat seat31 = new Seat("1A", 1, 1, false, 300);
		Seat seat32 = new Seat("1B", 1, 1, false, 300);
		Seat seat33 = new Seat("1C", 1, 1, false, 300);
		Seat seat34 = new Seat("1D", 1, 1, false, 300);
		Seat seat35 = new Seat("1E", 1, 1, false, 300);

		Seat seat36 = new Seat("2A", 2, 2, false, 200);
		Seat seat37 = new Seat("2B", 2, 2, false, 200);
		Seat seat38 = new Seat("2C", 2, 2, false, 200);
		Seat seat39 = new Seat("2D", 2, 2, false, 200);
		Seat seat40 = new Seat("2E", 2, 2, false, 200);

		Seat seat41 = new Seat("3A", 3, 3, false, 50);
		Seat seat42 = new Seat("3B", 3, 3, false, 50);
		Seat seat43 = new Seat("3C", 3, 3, false, 50);
		Seat seat44 = new Seat("3D", 3, 3, false, 50);
		Seat seat45 = new Seat("3E", 3, 3, false, 50);

		//seats 46 to 60
		Seat seat46 = new Seat("1A", 1, 1, false, 300);
		Seat seat47 = new Seat("1B", 1, 1, false, 300);
		Seat seat48 = new Seat("1C", 1, 1, false, 300);
		Seat seat49 = new Seat("1D", 1, 1, false, 300);
		Seat seat50 = new Seat("1E", 1, 1, false, 300);

		Seat seat51 = new Seat("2A", 2, 2, false, 200);
		Seat seat52 = new Seat("2B", 2, 2, false, 200);
		Seat seat53 = new Seat("2C", 2, 2, false, 200);
		Seat seat54 = new Seat("2D", 2, 2, false, 200);
		Seat seat55 = new Seat("2E", 2, 2, false, 200);

		Seat seat56 = new Seat("3A", 3, 3, false, 50);
		Seat seat57 = new Seat("3B", 3, 3, false, 50);
		Seat seat58 = new Seat("3C", 3, 3, false, 50);
		Seat seat59 = new Seat("3D", 3, 3, false, 50);
		Seat seat60 = new Seat("3E", 3, 3, false, 50);

		//seats 61 to 75 to flight 5
		Seat seat61 = new Seat("1A", 1, 1, false, 300);
		Seat seat62 = new Seat("1B", 1, 1, false, 300);
		Seat seat63 = new Seat("1C", 1, 1, false, 300);
		Seat seat64 = new Seat("1D", 1, 1, false, 300);
		Seat seat65 = new Seat("1E", 1, 1, false, 300);

		Seat seat66 = new Seat("2A", 2, 2, false, 200);
		Seat seat67 = new Seat("2B", 2, 2, false, 200);
		Seat seat68 = new Seat("2C", 2, 2, false, 200);
		Seat seat69 = new Seat("2D", 2, 2, false, 200);
		Seat seat70 = new Seat("2E", 2, 2, false, 200);

		Seat seat71 = new Seat("3A", 3, 3, false, 50);
		Seat seat72 = new Seat("3B", 3, 3, false, 50);
		Seat seat73 = new Seat("3C", 3, 3, false, 50);
		Seat seat74 = new Seat("3D", 3, 3, false, 50);
		Seat seat75 = new Seat("3E", 3, 3, false, 50);


		//seats 76 to 90
		Seat seat76 = new Seat("1A", 1, 1, false, 300);
		Seat seat77 = new Seat("1B", 1, 1, false, 300);
		Seat seat78 = new Seat("1C", 1, 1, false, 300);
		Seat seat79 = new Seat("1D", 1, 1, false, 300);
		Seat seat80 = new Seat("1E", 1, 1, false, 300);

		Seat seat81 = new Seat("2A", 2, 2, false, 200);
		Seat seat82 = new Seat("2B", 2, 2, false, 200);
		Seat seat83 = new Seat("2C", 2, 2, false, 200);
		Seat seat84 = new Seat("2D", 2, 2, false, 200);
		Seat seat85 = new Seat("2E", 2, 2, false, 200);

		Seat seat86 = new Seat("3A", 3, 3, false, 50);
		Seat seat87 = new Seat("3B", 3, 3, false, 50);
		Seat seat88 = new Seat("3C", 3, 3, false, 50);
		Seat seat89 = new Seat("3D", 3, 3, false, 50);
		Seat seat90 = new Seat("3E", 3, 3, false, 50);



		// Ticket ticket1 = new Ticket(150.0f, false, false);
		// Ticket ticket2 = new Ticket(200.0f, false, false);
		// Ticket ticket3 = new Ticket(180.0f, false, false);
		// Ticket ticket4 = new Ticket(220.0f, false, false);
		// Ticket ticket5 = new Ticket(190.0f, false, false);
		
		// Payment payment1 = new Payment("1234567890123456", "12/25", 123, 200);
		// Payment payment2 = new Payment("9876543210987654", "10/24", 456, 150);
		// Payment payment3 = new Payment("1111222233334444", "08/23", 789, 180);
		// Payment payment4 = new Payment("5555666677778888", "06/22", 321, 220);
		// Payment payment5 = new Payment("9999888877776666", "04/21", 654, 190);

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

		//Set all seats to flight 4
		seat46.setFlight(flight4);
		seat47.setFlight(flight4);
		seat48.setFlight(flight4);
		seat49.setFlight(flight4);
		seat50.setFlight(flight4);
		seat51.setFlight(flight4);
		seat52.setFlight(flight4);
		seat53.setFlight(flight4);
		seat54.setFlight(flight4);
		seat55.setFlight(flight4);
		seat56.setFlight(flight4);
		seat57.setFlight(flight4);
		seat58.setFlight(flight4);
		seat59.setFlight(flight4);
		seat60.setFlight(flight4);

		//Set all seats to flight 5
		seat61.setFlight(flight5);
		seat62.setFlight(flight5);
		seat63.setFlight(flight5);
		seat64.setFlight(flight5);
		seat65.setFlight(flight5);
		seat66.setFlight(flight5);
		seat67.setFlight(flight5);
		seat68.setFlight(flight5);
		seat69.setFlight(flight5);
		seat70.setFlight(flight5);
		seat71.setFlight(flight5);
		seat72.setFlight(flight5);
		seat73.setFlight(flight5);
		seat74.setFlight(flight5);
		seat75.setFlight(flight5);

		//Set all seats to flight 6
		seat76.setFlight(flight6);
		seat77.setFlight(flight6);
		seat78.setFlight(flight6);
		seat79.setFlight(flight6);
		seat80.setFlight(flight6);
		seat81.setFlight(flight6);
		seat82.setFlight(flight6);
		seat83.setFlight(flight6);
		seat84.setFlight(flight6);
		seat85.setFlight(flight6);
		seat86.setFlight(flight6);
		seat87.setFlight(flight6);
		seat88.setFlight(flight6);
		seat89.setFlight(flight6);
		seat90.setFlight(flight6);



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
		seatInterface.save(seat46);
		seatInterface.save(seat47);
		seatInterface.save(seat48);
		seatInterface.save(seat49);
		seatInterface.save(seat50);
		seatInterface.save(seat51);
		seatInterface.save(seat52);
		seatInterface.save(seat53);
		seatInterface.save(seat54);
		seatInterface.save(seat55);
		seatInterface.save(seat56);
		seatInterface.save(seat57);
		seatInterface.save(seat58);
		seatInterface.save(seat59);
		seatInterface.save(seat60);
		seatInterface.save(seat61);
		seatInterface.save(seat62);
		seatInterface.save(seat63);
		seatInterface.save(seat64);
		seatInterface.save(seat65);
		seatInterface.save(seat66);
		seatInterface.save(seat67);
		seatInterface.save(seat68);
		seatInterface.save(seat69);
		seatInterface.save(seat70);
		seatInterface.save(seat71);
		seatInterface.save(seat72);
		seatInterface.save(seat73);
		seatInterface.save(seat74);
		seatInterface.save(seat75);
		seatInterface.save(seat76);
		seatInterface.save(seat77);
		seatInterface.save(seat78);
		seatInterface.save(seat79);
		seatInterface.save(seat80);
		seatInterface.save(seat81);
		seatInterface.save(seat82);
		seatInterface.save(seat83);
		seatInterface.save(seat84);
		seatInterface.save(seat85);
		seatInterface.save(seat86);
		seatInterface.save(seat87);
		seatInterface.save(seat88);
		seatInterface.save(seat89);
		seatInterface.save(seat90);

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

		// ticket1.setPassenger(customer1);
		// ticket1.setFlight(flight1);
		// ticket1.setSeat(seat1);

		// ticket2.setPassenger(customer2);
		// ticket2.setFlight(flight2);
		// ticket2.setSeat(seat2);

		// ticketInterface.save(ticket1);
		// ticketInterface.save(ticket2);

		// payment1.setTicket(ticket1);
		// paymentInterface.save(payment1);

	}
}
