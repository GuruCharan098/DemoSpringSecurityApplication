package com.example.demoSpringSecureApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringSecurityAppApplication.class, args);
		
		System.out.println("Welcome to Spring Security App");
	}

}
