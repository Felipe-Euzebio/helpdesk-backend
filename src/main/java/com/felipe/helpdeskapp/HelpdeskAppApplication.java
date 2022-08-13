package com.felipe.helpdeskapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelpdeskAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskAppApplication.class, args);
	}
	
	
	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**") .allowedOrigins("http://localhost:4200"); } }; }
	 */
	
}
