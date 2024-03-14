package com.projects.sunrise_hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:5173")
public class SunriseHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunriseHotelApplication.class, args);
	}

}
