package com.hapvida.hapvidaapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class HapvidaAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HapvidaAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
