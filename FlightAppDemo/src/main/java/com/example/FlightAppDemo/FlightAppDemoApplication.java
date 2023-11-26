package com.example.FlightAppDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.FlightAppDemo.controller.AircraftRepository;
import com.example.FlightAppDemo.controller.CustomerRepository;
import com.example.FlightAppDemo.controller.FlightRepository;
import com.example.FlightAppDemo.model.Aircraft;
import com.example.FlightAppDemo.model.Customer;
import com.example.FlightAppDemo.model.Flight;

@SpringBootApplication  //combination of three annotations
public class FlightAppDemoApplication implements CommandLineRunner{

	@Autowired
	AircraftRepository repoInterface;

	@Autowired
	CustomerRepository custInterface;

	@Autowired
	FlightRepository flightInterface;

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

		// Creating Flight objects using the parameterized constructor
		Flight flight1 = new Flight(1, "2023-12-01", "08:00", "2023-12-01", "10:30", "New York", "USA", "JFK",
				"London", "UK", "Heathrow", aircraft1);

		Flight flight2 = new Flight(2, "2023-12-02", "12:00", "2023-12-02", "18:30", "Paris", "France", "CDG",
				"Tokyo", "Japan", "Narita", aircraft2);

		Flight flight3 = new Flight(3, "2023-12-03", "10:30", "2023-12-03", "13:45", "Sydney", "Australia", "SYD",
				"Dubai", "UAE", "DXB", aircraft3);

		Flight flight4 = new Flight(4, "2023-12-04", "09:15", "2023-12-04", "15:00", "Los Angeles", "USA", "LAX",
				"Beijing", "China", "PEK", aircraft4);

		Flight flight5 = new Flight(5, "2023-12-05", "16:45", "2023-12-05", "19:20", "Moscow", "Russia", "SVO",
				"Singapore", "Singapore", "Changi", aircraft5);


		custInterface.save(customer1);
		custInterface.save(customer2);
		custInterface.save(customer3);
		custInterface.save(customer4);
		custInterface.save(customer5);

		repoInterface.save(aircraft1);
		repoInterface.save(aircraft2);
		repoInterface.save(aircraft3);
		repoInterface.save(aircraft4);
		repoInterface.save(aircraft5);

		flightInterface.save(flight1);
		flightInterface.save(flight2);
		flightInterface.save(flight3);
		flightInterface.save(flight4);
		flightInterface.save(flight5);

		repoInterface.findByowned(true).forEach(
			val -> System.out.println(val)
		);

		custInterface.findByname("Diana").forEach(
			val -> System.out.println(val)
		);
	}
}
