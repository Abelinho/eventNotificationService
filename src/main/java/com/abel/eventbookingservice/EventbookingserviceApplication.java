package com.abel.eventbookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@EnableScheduling
@SpringBootApplication
public class EventbookingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventbookingserviceApplication.class, args);
	}

}
