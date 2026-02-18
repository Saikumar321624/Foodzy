package com.example.Foodzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FoodzyApplication {
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(FoodzyApplication.class, args);
	}
	
	@Bean
	public RestTemplate Resttemplate() {
		return new RestTemplate();
	}

}
