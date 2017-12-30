package com.gi.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import de.codecentric.boot.admin.config.EnableAdminServer;

@EnableAdminServer
@Configuration
@SpringBootApplication
public class SpringbootAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAdminServerApplication.class, args);
	}
}	
