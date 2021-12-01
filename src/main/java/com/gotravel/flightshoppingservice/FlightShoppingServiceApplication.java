package com.gotravel.flightshoppingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableEurekaClient
public class FlightShoppingServiceApplication implements WebMvcConfigurer {

	public static void main(final String[] args) {
		SpringApplication.run(FlightShoppingServiceApplication.class, args);
	}

	@Override
	public void addCorsMappings(final CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins(
						"http://localhost:4200"
				)
				.allowedMethods(
						"GET",
						"PUT",
						"POST",
						"DELETE",
						"PATCH",
						"OPTIONS"
				);
	}

}
