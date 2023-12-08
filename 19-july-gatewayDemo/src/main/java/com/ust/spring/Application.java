package com.ust.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route(p -> p
	            .path("/get")
	            .filters(f -> f.addRequestHeader("Hello", "World"))
	            .uri("http://httpbin.org:80"))
	        .build();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Hello Hai Its Working....");
	}

}
