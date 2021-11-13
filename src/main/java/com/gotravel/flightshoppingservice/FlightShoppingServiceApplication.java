package com.gotravel.flightshoppingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class FlightShoppingServiceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(FlightShoppingServiceApplication.class, args);
	}

}
