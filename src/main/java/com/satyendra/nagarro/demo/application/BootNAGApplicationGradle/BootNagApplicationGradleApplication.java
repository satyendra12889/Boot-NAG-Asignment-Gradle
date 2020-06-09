package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  
public class BootNagApplicationGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootNagApplicationGradleApplication.class, args);
	}

}
