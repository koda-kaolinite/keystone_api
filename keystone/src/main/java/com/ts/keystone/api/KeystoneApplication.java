package com.ts.keystone.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class KeystoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeystoneApplication.class, args);
	}

}
