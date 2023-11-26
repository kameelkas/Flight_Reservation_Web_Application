package com.example.FlightAppDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //combination of three annotations
public class FlightAppDemoApplication implements CommandLineRunner{

	@Autowired
	AircraftRepository repoInterface;

	@Autowired
	CustomerRepository custInterface;

	public static void main(String[] args) {
		SpringApplication.run(FlightAppDemoApplication.class, args);
	}

	@Override
	public void run(String... args){
		repoInterface.save(new Aircraft("Boeing 757", 30, 20, true));
		repoInterface.save(new Aircraft("Airbus A320", 150, 25, false));
		repoInterface.save(new Aircraft("Cessna 172", 4, 5, true));
		repoInterface.save(new Aircraft("Embraer E190", 100, 22, false));
		repoInterface.save(new Aircraft("Boeing 787", 250, 30, true));

		Customer customer1 = new Customer("Alice", "alice@example.com", "+1234567890");
		Customer customer2 = new Customer("Bob", "bob@example.com", "+1987654321");
		Customer customer3 = new Customer("Charlie", "charlie@example.com", "+1654321890");
		Customer customer4 = new Customer("Diana", "diana@example.com", "+1789456231");
		Customer customer5 = new Customer("Eve", "eve@example.com", "+1567894321");

		custInterface.save(customer1);
		custInterface.save(customer2);
		custInterface.save(customer3);
		custInterface.save(customer4);
		custInterface.save(customer5);


		repoInterface.findByowned(true).forEach(
			val -> System.out.println(val)
		);

		custInterface.findByname("Diana").forEach(
			val -> System.out.println(val)
		);
	}
}
