package com.example.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoPApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoPApplication.class, args);
		System.out.println("Working Directory: " + System.getProperty("user.dir"));

	}

}
