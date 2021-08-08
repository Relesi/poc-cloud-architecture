package com.relesi.cloudarchitecture.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;


//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
@EnableCaching
public class CloudArchitectureApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CloudArchitectureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
