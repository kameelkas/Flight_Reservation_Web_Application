package com.example.FlightAppDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //combination of three annotations
public class FlightAppDemoApplication implements CommandLineRunner{

	@Autowired
	RepoInterface repoInterface;

	public static void main(String[] args) {
		SpringApplication.run(FlightAppDemoApplication.class, args);
	}

	@Override
	public void run(String... args){
		Aircraft aircraft1 = new Aircraft("Boeing 757", 30, 20, true);
		repoInterface.save(aircraft1);


		repoInterface.findBymodel("Boeing 757").forEach(
			val -> System.out.println(val)
		);
	}
}
